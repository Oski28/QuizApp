package com.example.projectwebjava.repository;

import com.example.projectwebjava.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    String FIND_CORRECT = "SELECT CASE WHEN a.correct = 1 THEN true ELSE false END FROM Answer a WHERE a.id = :id";

    @Query(value = FIND_CORRECT)
    boolean getCorrectById(@Param("id") Long id);
}
