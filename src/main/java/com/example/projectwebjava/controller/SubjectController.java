package com.example.projectwebjava.controller;

import com.example.projectwebjava.model.Subject;
import com.example.projectwebjava.service.SubjectService;
import com.example.projectwebjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    private final Integer PAGE_SIZE = 5;

    private SubjectService subjectService;
    private UserService userService;

    @Autowired
    public SubjectController(SubjectService subjectService, UserService userService) {
        this.subjectService = subjectService;
        this.userService = userService;
    }

    @GetMapping
    public String getSubjects(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        model.addAttribute("subject", new Subject());

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(PAGE_SIZE);
        Page<Subject> subjectPage = userService.getAuthUserSubjects(PageRequest.of(currentPage-1,pageSize));
        model.addAttribute("subjects", subjectPage);

        int totalPages = subjectPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("users", userService.getAllUsersByAuthenticatedUserRoleWithoutAdmin());
        return "subjects";
    }

    @PostMapping
    public String saveSubject(@Valid @ModelAttribute("subject") Subject subject,
                              BindingResult bindingResult,
                              @RequestParam(value = "users", required = false) List<String> logins,
                              Model model) {
        if (bindingResult.hasErrors()) {
            Page<Subject> subjectPage = userService.getAuthUserSubjects(PageRequest.of(0,PAGE_SIZE));
            model.addAttribute("subjects", subjectPage);

            int totalPages = subjectPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("users", userService.getAllUsersByAuthenticatedUserRoleWithoutAdmin());
            return "subjects";
        }
        subjectService.addSubject(subject);
        if (logins == null) {
            userService.addSubjectToAdmin(subject);
        } else {
            userService.addSubjectToUsers(logins, subject);
        }
        return "redirect:/subject";
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return "redirect:/subject";
    }

    @GetMapping("/edit/{id}")
    public String editSubject(@PathVariable Long id, Model model) {
        model.addAttribute("subject", subjectService.getSubjectById(id));
        model.addAttribute("users", userService.getAllUsersByAuthenticatedUserRoleWithoutAdmin());
        model.addAttribute("subjectUsers", subjectService.getUsersWithAccessToSubject(id));
        return "editSubject";
    }

    @PutMapping("/edit/{id}")
    public String editSubject(@Valid @ModelAttribute Subject subject, BindingResult bindingResult,
                              @RequestParam(value = "users", required = false) List<String> logins,
                              @PathVariable Long id,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsersByAuthenticatedUserRoleWithoutAdmin());
            model.addAttribute("subjectUsers", subjectService.getUsersWithAccessToSubject(id));
            return "editSubject";
        }
        subjectService.updateSubject(subject);
        if (logins != null) {
            userService.updateUsersSubject(logins, subject);
        } else {
            userService.updateAdminSubject(subject);
        }
        return "redirect:/subject";
    }
}
