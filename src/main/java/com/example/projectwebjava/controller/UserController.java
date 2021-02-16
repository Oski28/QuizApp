package com.example.projectwebjava.controller;

import com.example.projectwebjava.constraint.ValidPassword;
import com.example.projectwebjava.exception.PasswordException;
import com.example.projectwebjava.model.User;
import com.example.projectwebjava.service.UserRoleService;
import com.example.projectwebjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Integer PAGE_SIZE = 5;

    private UserService userService;
    private UserRoleService userRoleService;

    @Autowired
    public UserController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public String getUsers(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(PAGE_SIZE);
        Page<User> userPage = userService.getUsersByAuthenticatedUserRoleWithAdmin(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("userRoles", userRoleService.getRolesToAddOrEditUser());
        model.addAttribute("userSubjects", userService.getAuthUserSubjects());
        model.addAttribute("user", new User());
        model.addAttribute("confirmPassword", new String());
        model.addAttribute("users", userPage);
        model.addAttribute("adminRole", userRoleService.getRoleByRole("ROLE_ADMIN"));
        model.addAttribute("checked", userRoleService.getRoleByRole("ROLE_STUDENT"));

        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("userRoles", userRoleService.getRolesToAddOrEditUser());
        model.addAttribute("userSubjects", userService.getAuthUserSubjects());
        model.addAttribute("adminRole", userRoleService.getRoleByRole("ROLE_ADMIN"));
        model.addAttribute("confirmPassword", new String());
        return "editUser";
    }

    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           @RequestParam(value = "userRoles", required = false) String userRole,
                           @RequestParam(value = "userSubjects", required = false) List<String> subjects,
                           @RequestParam(value = "confirmPassword") String confirmPassword,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRoles", userRoleService.getRolesToAddOrEditUser());
            model.addAttribute("userSubjects", userService.getAuthUserSubjects());
            model.addAttribute("confirmPassword", new String());
            Page<User> userPage = userService.getUsersByAuthenticatedUserRoleWithAdmin(PageRequest.of(0, PAGE_SIZE));
            int totalPages = userPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("users", userPage);
            model.addAttribute("adminRole", userRoleService.getRoleByRole("ROLE_ADMIN"));
            model.addAttribute("checked", userRoleService.getRoleByRole("ROLE_STUDENT"));
            return "users";
        }
        if (subjects != null) {
            userService.addSubjectsToUser(user, subjects);
        }
        if (userRole == null) {
            userRole = "ROLE_STUDENT";
        }
        try {
            userService.addUserWithRole(user, userRole, confirmPassword);
        } catch (PasswordException e) {
            return "redirect:/user?errorPassword";
        } catch (SQLIntegrityConstraintViolationException e){
            return "redirect:/user?errorLogin";
        }
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @PutMapping("/edit/{id}")
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult,
                             @RequestParam(value = "userRoles", required = false) String userRole,
                             @RequestParam(value = "userSubjects", required = false) List<String> subjects,
                             @RequestParam(value = "confirmPassword") String confirmPassword,
                             @PathVariable Long id,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRoles", userRoleService.getRolesToAddOrEditUser());
            model.addAttribute("userSubjects", userService.getAuthUserSubjects());
            model.addAttribute("adminRole", userRoleService.getRoleByRole("ROLE_ADMIN"));
            model.addAttribute("confirmPassword", new String());
            return "editUser";
        }
        if (subjects != null) {
            userService.addSubjectsToUser(user, subjects);
        }
        if (userRole != null) {
            userService.setUserRole(user, userRole);
        }
        if (subjects == null && userRole == null) {
            userService.addDefaultRoleAndSubjects(user);
        }
        if (userRole == null) {
            userRole = "ROLE_STUDENT";
        }
        try {
            userService.updatePassword(user, confirmPassword);
        } catch (PasswordException e) {
            return "redirect:/user/edit/{id}?errorPassword";
        }
        try {
            userService.updateUser(user);
        } catch (SQLIntegrityConstraintViolationException e) {
            return "redirect:/user/edit/{id}?errorLogin";
        }
        return "redirect:/user";
    }
}
