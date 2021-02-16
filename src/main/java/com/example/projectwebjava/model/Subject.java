package com.example.projectwebjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "Subject must contain 1 or more characters.")
    @Size(min = 3,max = 60,message = "Subject must be between 3 and 60 characters in length.")
    private String name;
    @OneToMany(mappedBy = "subject",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    private List<Quiz> quizList = new ArrayList<>();

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quizList=" + quizList +
                '}';
    }
}
