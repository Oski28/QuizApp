package com.example.projectwebjava.service;

import com.example.projectwebjava.exception.NumberOfQuestionsException;
import com.example.projectwebjava.model.Question;
import com.example.projectwebjava.model.Quiz;
import com.example.projectwebjava.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class QuizService {

    private QuizRepository quizRepository;
    private SubjectService subjectService;
    private QuestionService questionService;

    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Page<Quiz> getQuizzesToSubject(Long id, PageRequest pageRequest) {
        return quizRepository.findAllBySubject(subjectService.getSubjectById(id),pageRequest);
    }

    public List<Quiz> getQuizzesToSubject(Long id) {
        return quizRepository.findAllBySubject(subjectService.getSubjectById(id));
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public void addQuestionsToQuiz(Quiz quiz, List<String> questions) throws NumberOfQuestionsException {
        if (questions != null) {
            if (quiz.getNumberOfQuestions() <= questions.size()) {
                for (String question : questions) {
                    Question quest = questionService.getQuestionByQuestion(question);
                    quiz.getQuestions().add(quest);
                }
            } else {
                throw new NumberOfQuestionsException("Ilość pytań nie może być większa niż ilość zaznaczonych pytań.");
            }
        } else {
            throw new NumberOfQuestionsException("Ilość dodanych pytań nie może być zerem.");
        }
    }

    public void addOrUpdateQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public Quiz getQuizById(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            return quiz.get();
        } else {
            throw new NullPointerException("Not found quiz with id" + id);
        }
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public void setSubjectToQuiz(Quiz quiz, Long subjectId) {
        quiz.setSubject(subjectService.getSubjectById(subjectId));
    }
}
