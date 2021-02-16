package com.example.projectwebjava.service;


import com.example.projectwebjava.exception.PasswordException;
import com.example.projectwebjava.model.Subject;
import com.example.projectwebjava.model.User;
import com.example.projectwebjava.repository.SubjectRepository;
import com.example.projectwebjava.repository.UserRepository;
import com.example.projectwebjava.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final String STUDENT_ROLE = "ROLE_STUDENT";
    private static final String TEACHER_ROLE = "ROLE_TEACHER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private boolean alreadySetup = false;

    private SubjectService subjectService;
    private UserService userService;

    @Autowired
    public SetupDataLoader(SubjectService subjectService, UserService userService) {
        this.subjectService = subjectService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        Subject subject1 = subjectService.getSubjectById(Long.valueOf(1));
        Subject subject2 = subjectService.getSubjectById(Long.valueOf(2));
        Subject subject3 = subjectService.getSubjectById(Long.valueOf(3));
        Subject subject4 = subjectService.getSubjectById(Long.valueOf(4));
        Subject subject5 = subjectService.getSubjectById(Long.valueOf(5));
        Subject subject6 = subjectService.getSubjectById(Long.valueOf(6));
        User user = new User("Admin1", "Admin1");
        user.setSubjects(Arrays.asList(subject1, subject2, subject3, subject4, subject5, subject6));

        User user2 = new User("Teacher1", "Teacher1");
        user2.setSubjects(Arrays.asList(subject1));

        User user3 = new User("Student1", "Student1");
        user3.setSubjects(Arrays.asList(subject1));
        try {
            userService.addUserWithRole(user, ADMIN_ROLE, user.getPassword());
            userService.addUserWithRole(user2, TEACHER_ROLE, user2.getPassword());
            userService.addUserWithRole(user3, STUDENT_ROLE, user3.getPassword());
        } catch (PasswordException | SQLIntegrityConstraintViolationException e) {
            e.getMessage();
        }
        alreadySetup = true;
    }
}
