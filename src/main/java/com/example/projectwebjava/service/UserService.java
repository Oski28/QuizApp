package com.example.projectwebjava.service;

import com.example.projectwebjava.exception.PasswordException;
import com.example.projectwebjava.model.Subject;
import com.example.projectwebjava.model.User;
import com.example.projectwebjava.model.UserRole;
import com.example.projectwebjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String STUDENT_ROLE = "ROLE_STUDENT";
    private static final String TEACHER_ROLE = "ROLE_TEACHER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";
    private UserRepository userRepository;
    private UserRoleService userRoleService;
    private SubjectService subjectService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleService userRoleService, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, SubjectService subjectService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.subjectService = subjectService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void addUserWithRole(User user, String role, String confirmPassword) throws PasswordException, SQLIntegrityConstraintViolationException {
        if (user.getPassword().equals(confirmPassword)) {
            Set<UserRole> roles;
            switch (role) {
                case STUDENT_ROLE:
                    roles = getRolesToUser(STUDENT_ROLE);
                    break;
                case TEACHER_ROLE:
                    roles = getRolesToUser(TEACHER_ROLE);
                    break;
                case ADMIN_ROLE:
                    roles = getRolesToUser(ADMIN_ROLE);
                    break;
                default:
                    throw new IllegalStateException("Nieznana wartość: " + role);
            }
            user.setRoles(roles);
            String passwordHash = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordHash);
            try {
                userRepository.save(user);
            } catch (DataIntegrityViolationException e) {
                throw new SQLIntegrityConstraintViolationException("Podany login już istnieje w bazie.");
            }
        } else {
            throw new PasswordException("Podane hasła się nie zgadzają");
        }
    }

    private Set<UserRole> getRolesToUser(String role) {
        Set<UserRole> rolesToAdd = new HashSet<>();
        switch (role) {
            case ADMIN_ROLE:
                rolesToAdd.add(userRoleService.getRoleByRole(ADMIN_ROLE));
            case TEACHER_ROLE:
                rolesToAdd.add(userRoleService.getRoleByRole(TEACHER_ROLE));
            case STUDENT_ROLE:
                rolesToAdd.add(userRoleService.getRoleByRole(STUDENT_ROLE));
                break;
            default:
                throw new IllegalStateException("Nieznana wartość: " + role);
        }
        return rolesToAdd;
    }

    public Page<User> getUsersByAuthenticatedUserRoleWithAdmin(PageRequest pageRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Page<User> users = null;
        if (auth != null) {
            if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(ADMIN_ROLE))) {
                users = userRepository.findAll(pageRequest);
            } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(TEACHER_ROLE))) {
                users = userRepository.findAllByRolesNotContainsAndSubjectsIn(
                        userRoleService.getRoleByRole(TEACHER_ROLE), getAuthenticatedUser().getSubjects(), pageRequest);
            }
        }
        return users;
    }

    public Page<User> getUsersByAuthenticatedUserRoleWithoutAdmin(PageRequest pageRequest) {
        return new PageImpl<>(getUsersByAuthenticatedUserRoleWithAdmin(pageRequest).stream()
                .filter(user -> user.getRoles().stream().noneMatch(userRole -> userRole.getRole().equals(ADMIN_ROLE))).collect(Collectors.toList()));
    }

    public List<User> getAllUsersByAuthenticatedUserRoleWithoutAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<User> users = null;
        if (auth != null) {
            if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(ADMIN_ROLE))) {
                users = userRepository.findAll();
            } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(TEACHER_ROLE))) {
                users = userRepository.findAllByRolesNotContainsAndSubjectsIn(userRoleService.getRoleByRole(TEACHER_ROLE), getAuthenticatedUser().getSubjects());
            }
        }
        return users.stream().filter(user -> user.getRoles().stream().noneMatch(userRole -> userRole.getRole().equals(ADMIN_ROLE))).collect(Collectors.toList());
    }

    public void addSubjectToUsers(List<String> logins, Subject subject) {
        for (String login : logins) {
            User user = userRepository.findByLogin(login);
            user.getSubjects().add(subject);
            userRepository.save(user);
        }
        addSubjectToAdmin(subject);
    }

    public void addSubjectToAdmin(Subject subject) {
        User user = userRepository.findByRolesContains(userRoleService.getRoleByRole(ADMIN_ROLE));
        user.getSubjects().add(subject);
        userRepository.save(user);
    }

    public void addSubjectsToUser(User user, List<String> subjects) {
        for (String subject : subjects) {
            Subject sub = subjectService.getSubjectByName(subject);
            user.getSubjects().add(sub);
        }
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user.getRoles().contains(userRoleService.getRoleByRole(ADMIN_ROLE))) {
            throw new IllegalArgumentException("You cannot delete admin user.");
        } else {
            userRepository.deleteById(id);
        }
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NullPointerException("Not found user with id" + id);
        }
    }

    public void updateUser(User user) throws SQLIntegrityConstraintViolationException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(ADMIN_ROLE)
                && user.getRoles().contains(userRoleService.getRoleByRole(ADMIN_ROLE)))) {
            userRepository.save(user);
            Authentication request = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        }
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Podany login już istnieje w bazie.");
        }
    }

    public void setUserRole(User user, String userRole) {
        user.setRoles(getRolesToUser(userRole));
    }

    public void updatePassword(User user, String confirmPassword) throws PasswordException {
        if (!user.getPassword().equals(confirmPassword)) {
            throw new PasswordException("Podane hasła się nie zgadzają");
        }
    }

    public void removeSubjectFromUsers(Subject subject) {
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.getSubjects().remove(subject));
    }

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByLogin(auth.getName());
    }

    public List<Subject> getAuthUserSubjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            User user = userRepository.getByLogin(auth.getName());
            return user.getSubjects();
        }
        return null;
    }

    public Page<Subject> getAuthUserSubjects(PageRequest pageRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            User user = userRepository.getByLogin(auth.getName());
            List<Subject> subjects = user.getSubjects();
            int pageSize = pageRequest.getPageSize();
            int currentPage = pageRequest.getPageNumber();
            int startItem = currentPage * pageSize;
            List<Subject> subList;

            if (subjects.size() < startItem) {
                subList = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, subjects.size());
                subList = subjects.subList(startItem, toIndex);
            }

            Page<Subject> subjectPage
                    = new PageImpl<>(subList, PageRequest.of(currentPage, pageSize), subjects.size());

            return subjectPage;
        }
        return null;
    }

    List<User> getAllContainsSubject(Subject subject) {
        return userRepository.findAllBySubjectsContains(subject);
    }

    public void updateUsersSubject(List<String> logins, Subject subject) {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.getSubjects().removeIf(subject1 ->
                    subject1.getId().equals(subject.getId())
            );
            if (logins.contains(user.getLogin()) || user.getRoles().contains(userRoleService.getRoleByRole(ADMIN_ROLE))) {
                user.getSubjects().add(subject);
            }
            userRepository.save(user);
        });
    }

    public void updateAdminSubject(Subject subject) {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.getSubjects().removeIf(subject1 ->
                    subject1.getId().equals(subject.getId())
            );
            if (user.getRoles().contains(userRoleService.getRoleByRole(ADMIN_ROLE))) {
                user.getSubjects().add(subject);
            }
            userRepository.save(user);
        });
    }

    public void addDefaultRoleAndSubjects(User user) {
        if (userRepository.findAllIdContainsRole(userRoleService.getRoleByRole(ADMIN_ROLE)).contains(user.getId())) {
            user.setSubjects(subjectService.getAllSubjects());
            setUserRole(user, ADMIN_ROLE);
        } else {
            setUserRole(user, STUDENT_ROLE);
        }
    }
}