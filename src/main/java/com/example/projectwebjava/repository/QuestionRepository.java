package com.example.projectwebjava.repository;

import com.example.projectwebjava.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findByText(String text);
}
