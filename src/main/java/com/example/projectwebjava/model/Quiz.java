package com.example.projectwebjava.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<>();
    @NotNull(message = "Time cannot be null.")
    @Min(value = 1,message = "Time must be more than 1 minute.")
    @Max(value = 60,message = "Time must be less than 60 minutes.")
    private Integer time;
    @NotNull(message = "Number of questions cannot be null.")
    @Min(value = 1,message = "Number of questions must be more than 1.")
    private Integer numberOfQuestions;
    @ManyToOne
    private Subject subject;

    public Quiz() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", questions=" + questions +
                ", time=" + time +
                ", numberOfQuestions=" + numberOfQuestions +
                ", subject=" + subject.getId() +
                '}';
    }
}
