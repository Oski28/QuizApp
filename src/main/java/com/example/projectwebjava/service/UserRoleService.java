package com.example.projectwebjava.service;

import com.example.projectwebjava.model.UserRole;
import com.example.projectwebjava.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserRoleService {

    private static final String STUDENT_ROLE = "ROLE_STUDENT";
    private static final String TEACHER_ROLE = "ROLE_TEACHER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> getRolesToAddOrEditUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<UserRole> userRoles = new ArrayList<>();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(ADMIN_ROLE))) {
            userRoles.add(userRoleRepository.findByRole(STUDENT_ROLE));
            userRoles.add(userRoleRepository.findByRole(TEACHER_ROLE));
        } else {
            userRoles.add(userRoleRepository.findByRole(STUDENT_ROLE));
        }
        return userRoles;
    }

    public UserRole getRoleByRole(String role) {
        return userRoleRepository.findByRole(role);
    }

    public List<UserRole> getAllRoles() {
        return userRoleRepository.findAll();
    }
}
