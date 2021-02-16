package com.example.projectwebjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "Question must contain 1 or more characters.")
    @Size(min = 10,max = 200,message = "Question must be between 10 and 200 characters in length.")
    private String text;
    @NotEmpty(message = "Answers cannot be empty.")
    @Size(min = 2,message = "Answers must have 2 or more positions.")
    @OneToMany(mappedBy = "question",cascade = {CascadeType.ALL})
    private List<Answer> answers = new ArrayList<>();

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + text + '\'' +
                ", answers=" + answers +
                '}';
    }
}
