package com.example.projectwebjava.repository;

import com.example.projectwebjava.model.Subject;
import com.example.projectwebjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject getSubjectByName(String subject);
}
