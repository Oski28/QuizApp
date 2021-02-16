package com.example.projectwebjava.repository;

import com.example.projectwebjava.model.Subject;
import com.example.projectwebjava.model.User;
import com.example.projectwebjava.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    void deleteById(Long id);

    User getByLogin(String login);

    User findByRolesContains(UserRole userRole);

    List<User> findAllBySubjectsContains(Subject subject);

    @Query(value = "SELECT u.id FROM User u WHERE :role MEMBER OF u.roles")
    List<Long> findAllIdContainsRole(@Param("role") UserRole role);

    Page<User> findAllByRolesNotContainsAndSubjectsIn(UserRole roleByRole, List<Subject> subjects, PageRequest pageRequest);

    List<User> findAllByRolesNotContainsAndSubjectsIn(UserRole roleByRole, List<Subject> subjects);
}
