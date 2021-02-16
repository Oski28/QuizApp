package com.example.projectwebjava.service;

import com.example.projectwebjava.model.Answer;
import com.example.projectwebjava.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getEmptyListOfAnswers(int size) {
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            answers.add(new Answer());
        }
        return answers;
    }

    public boolean getCorrectToAnswer(Answer answer) {
        return answerRepository.getCorrectById(answer.getId());
    }

    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public void deleteAnswer(Answer answer){
        answerRepository.deleteById(answer.getId());
    }

}
