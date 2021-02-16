package com.example.projectwebjava.repository;

import com.example.projectwebjava.model.Quiz;
import com.example.projectwebjava.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Page<Quiz> findAllBySubject(Subject subject, Pageable pageable);

    List<Quiz> findAllBySubject(Subject subject);

}
