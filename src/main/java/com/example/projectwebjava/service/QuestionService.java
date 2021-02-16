package com.example.projectwebjava.service;

import com.example.projectwebjava.exception.AnswerNullTextException;
import com.example.projectwebjava.exception.AnswersSizeException;
import com.example.projectwebjava.model.Answer;
import com.example.projectwebjava.model.Question;
import com.example.projectwebjava.model.Quiz;
import com.example.projectwebjava.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionService {

    private final int NUMBER_OF_ANSWERS = 5;

    private QuestionRepository questionRepository;
    private QuizService quizService;
    private AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    public Page<Question> getQuestionsPage(PageRequest pageRequest) {
        return questionRepository.findAll(pageRequest);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionByQuestion(String text) {
        return questionRepository.findByText(text);
    }

    public void addQuestion(Question question) throws AnswersSizeException {
        List<Answer> answers = question.getAnswers();
        List<Answer> addedAnswers = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            answers.get(i).setQuestion(question);
            if (!answers.get(i).getText().equals(""))
                addedAnswers.add(answers.get(i));
        }
        question.setAnswers(addedAnswers);
        try {
            questionRepository.save(question);
        } catch (ConstraintViolationException e) {
            throw new AnswersSizeException("Answers must have 2 or more positions");
        }
        for (Answer addedAnswer : addedAnswers) {
            answerService.saveAnswer(addedAnswer);
        }
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Question getQuestionById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new NullPointerException("Not found question with id" + id);
        }
    }

    public void updateQuestion(Question question) throws AnswersSizeException, AnswerNullTextException {
        List<Answer> answers = question.getAnswers();
        List<Answer> toDelete = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).getText().equals("")) {
                toDelete.add(answers.get(i));
            } else {
                answers.get(i).setQuestion(question);
            }
        }
        for (Answer answer : toDelete) {
            question.getAnswers().remove(answer);
        }
        if (question.getAnswers().size() < 2) {
            throw new AnswersSizeException("Answers must have 2 or more positions");
        }
        try {
            questionRepository.save(question);
        } catch (ConstraintViolationException | TransactionSystemException e) {
            throw new AnswerNullTextException("Answer must contain 1 or more characters.");
        }
        for (Answer answer : toDelete) {
            if (answer.getId() != null)
                answerService.deleteAnswer(answer);
        }
    }

    public Question resizeAnswerList(Question question) {
        if (question.getAnswers().size() != NUMBER_OF_ANSWERS) {
            int resize = NUMBER_OF_ANSWERS - question.getAnswers().size();
            while (resize > 0) {
                question.getAnswers().add(new Answer());
                resize--;
            }
        }
        return question;
    }

    public Question getEmptyQuestion() {
        Question question = new Question();
        question.setAnswers(answerService.getEmptyListOfAnswers(NUMBER_OF_ANSWERS));
        return question;
    }

    public void removeQuestionFromQuizzes(Question question) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        for (Quiz quiz : quizzes) {
            if (quiz.getNumberOfQuestions() <= 2) {
                quizService.deleteQuiz(quiz.getId());
            } else {
                if ((quiz.getQuestions().size() - 1) < quiz.getNumberOfQuestions()) {
                    quiz.setNumberOfQuestions(quiz.getQuestions().size() - 1);
                }
                quiz.getQuestions().remove(question);
                quizService.addOrUpdateQuiz(quiz);
            }
        }
    }
}
