package com.example.projectwebjava.model;

import java.util.List;

public class QuizResult {

    private List<Question> questions;
    private int points;
    private List<Boolean> correct;

    public QuizResult(List<Question> questions, int points, List<Boolean> correct) {
        this.questions = questions;
        this.points = points;
        this.correct = correct;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Boolean> getCorrect() {
        return correct;
    }

    public void setCorrect(List<Boolean> correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "questions=" + questions +
                ", points=" + points +
                ", correct=" + correct +
                '}';
    }
}
