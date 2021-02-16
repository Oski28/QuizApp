package com.example.projectwebjava.service;

import com.example.projectwebjava.model.Subject;
import com.example.projectwebjava.model.User;
import com.example.projectwebjava.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class SubjectService {

    private SubjectRepository subjectRepository;
    private UserService userService;
    private QuizService quizService;

    @Autowired
    public void setSubjectRepository(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Subject getSubjectByName(String name) {
        return subjectRepository.getSubjectByName(name);
    }

    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        userService.removeSubjectFromUsers(getSubjectById(id));
        subjectRepository.deleteById(id);
    }

    public Subject getSubjectById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return subject.get();
        } else {
            throw new NullPointerException("Not found subject with id" + id);
        }
    }

    public void updateSubject(Subject subject) {
        subject.setQuizList(quizService.getQuizzesToSubject(subject.getId()));
        subjectRepository.save(subject);
    }

    public List<User> getUsersWithAccessToSubject(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return userService.getAllContainsSubject(subject.get());
        }
        return null;
    }

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }
}
