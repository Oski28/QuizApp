package com.example.projectwebjava.service;

import com.example.projectwebjava.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class QuizProgressService {

    private UserService userService;
    private AnswerService answerService;

    @Autowired
    public QuizProgressService(UserService userService, AnswerService answerService) {
        this.userService = userService;
        this.answerService = answerService;
    }

    public Page<Quiz> getAllAvailableQuizzesForUser(PageRequest pageRequest) {
        List<Quiz> quizzes = new ArrayList<>();
        User user = userService.getAuthenticatedUser();
        for (Subject subject : user.getSubjects()) {
            for (Quiz quiz : subject.getQuizList()) {
                quizzes.add(quiz);
            }
        }
        int pageSize = pageRequest.getPageSize();
        int currentPage = pageRequest.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Quiz> subList;

        if (quizzes.size() < startItem) {
            subList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, quizzes.size());
            subList = quizzes.subList(startItem, toIndex);
        }

        Page<Quiz> quizPage
                = new PageImpl<>(subList, PageRequest.of(currentPage, pageSize), quizzes.size());

        return quizPage;
    }

    public void randQuestions(Quiz quiz) {
        Random rand = new Random();
        List<Question> newQuestions = new ArrayList<>();
        for (int i = 0; i < quiz.getNumberOfQuestions(); i++) {
            int randomNumber = rand.nextInt(quiz.getQuestions().size());
            Question question = quiz.getQuestions().get(randomNumber);
            newQuestions.add(question);
            quiz.getQuestions().remove(question);
        }
        for (int i = 0; i < newQuestions.size(); i++) {
            newQuestions.get(i).setAnswers(randAnswers(newQuestions.get(i).getAnswers(), newQuestions.get(i).getAnswers().size()));
        }
        resetAllAnswers(newQuestions);
        quiz.setQuestions(newQuestions);
    }

    private List<Answer> randAnswers(List<Answer> answers, int size) {
        Random rand = new Random();
        List<Answer> newAnswers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int randomNumber = rand.nextInt(answers.size());
            Answer answer = answers.get(randomNumber);
            newAnswers.add(answer);
            answers.remove(answer);
        }
        return newAnswers;
    }

    private void resetAllAnswers(List<Question> questions) {
        questions.forEach(question -> question.getAnswers().forEach(answer -> answer.setCorrect(false)));
    }

    public QuizResult checkQuiz(Quiz quiz) {
        int correct = 0;
        List<Boolean> answers = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            boolean result = checkQuestion(question);
            if (result) {
                correct++;
            }
            answers.add(result);
        }
        return new QuizResult(quiz.getQuestions(), correct, answers);
    }

    private boolean checkQuestion(Question question) {
        for (Answer answer : question.getAnswers()) {
            boolean correct = answerService.getCorrectToAnswer(answer);
            if (answer.isCorrect() != correct)
                return false;
        }
        return true;
    }
}
