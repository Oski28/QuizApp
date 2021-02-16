package com.example.projectwebjava.controller;

import com.example.projectwebjava.exception.NumberOfQuestionsException;
import com.example.projectwebjava.model.Question;
import com.example.projectwebjava.model.Quiz;
import com.example.projectwebjava.service.QuestionService;
import com.example.projectwebjava.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("subject/{subjectId}/quiz")
public class QuizController {

    private final Integer PAGE_SIZE = 5;

    private QuizService quizService;
    private QuestionService questionService;

    @Autowired
    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping()
    public String getQuizzes(@PathVariable Long subjectId, Model model,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional <Integer>size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(PAGE_SIZE);
        Page<Quiz> quizPage = quizService.getQuizzesToSubject(subjectId,PageRequest.of(currentPage-1,pageSize));
        model.addAttribute("quizzes", quizPage);
        int totalPages = quizPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "quizzes";
    }

    @GetMapping("/add")
    public String addQuiz(Model model, @PathVariable Long subjectId) {
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("questions", questionService.getAllQuestions());
        model.addAttribute("subjectId", subjectId);
        return "addQuiz";
    }

    @PostMapping("/add")
    public String saveQuiz(@Valid @ModelAttribute("quiz") Quiz quiz,
                           BindingResult bindingResult,
                           @PathVariable Long subjectId,
                           @RequestParam(value = "quest", required = false) List<String> questions,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("questions", questionService.getAllQuestions());
            return "addQuiz";
        }
        quizService.setSubjectToQuiz(quiz, subjectId);
        try {
            quizService.addQuestionsToQuiz(quiz, questions);
        } catch (NumberOfQuestionsException e) {
            return "redirect:/subject/{subjectId}/quiz/add?error";
        }
        quizService.addOrUpdateQuiz(quiz);
        return "redirect:/subject/{subjectId}/quiz";
    }

    @DeleteMapping("/{id}")
    public String deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return "redirect:/subject/{subjectId}/quiz";
    }

    @GetMapping("/edit/{id}")
    public String editQuiz(@PathVariable Long id, @PathVariable Long subjectId, Model model) {
        model.addAttribute("quiz", quizService.getQuizById(id));
        model.addAttribute("questions", questionService.getAllQuestions());
        model.addAttribute("subjectId", subjectId);
        return "editQuiz";
    }

    @PutMapping("/edit/{id}")
    public String editQuiz(@Valid @ModelAttribute("quiz") Quiz quiz,
                           BindingResult bindingResult,
                           @PathVariable Long subjectId,
                           @RequestParam(value = "quest") List<String> questions,
                           @PathVariable Long id,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("questions", questionService.getAllQuestions());
            model.addAttribute("subjectId", subjectId);
            return "editQuiz";
        }
        quizService.setSubjectToQuiz(quiz, subjectId);
        if (questions != null) {
            try {
                quizService.addQuestionsToQuiz(quiz, questions);
            } catch (NumberOfQuestionsException e) {
                return "redirect:/subject/{subjectId}/quiz/edit/{id}?error";
            }
        }
        quizService.addOrUpdateQuiz(quiz);
        return "redirect:/subject/{subjectId}/quiz";
    }
}
