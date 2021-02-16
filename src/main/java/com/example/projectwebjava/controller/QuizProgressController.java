package com.example.projectwebjava.controller;

import com.example.projectwebjava.model.Quiz;
import com.example.projectwebjava.service.QuizProgressService;
import com.example.projectwebjava.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/quiz")
public class QuizProgressController {

    private final Integer PAGE_SIZE = 5;

    private QuizProgressService quizProgressService;
    private QuizService quizService;

    @Autowired
    public QuizProgressController(QuizProgressService quizProgressService, QuizService quizService) {
        this.quizProgressService = quizProgressService;
        this.quizService = quizService;
    }

    @GetMapping
    public String getQuizzesToComplete(Model model,
                                       @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(PAGE_SIZE);
        Page<Quiz> quizPage = quizProgressService.getAllAvailableQuizzesForUser(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("quizzes", quizPage);

        int totalPages = quizPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "quizzesList";
    }

    @GetMapping("/{id}")
    public String getQuizToComplete(@PathVariable Long id, Model model) {
        Quiz quiz = quizService.getQuizById(id);
        quizProgressService.randQuestions(quiz);
        model.addAttribute("quiz", quiz);
        return "quizProgress";
    }

    @PostMapping
    public String checkAnswers(@ModelAttribute("quiz") Quiz quiz, Model model) {
        model.addAttribute("quiz", quizProgressService.checkQuiz(quiz));
        return "quizResult";
    }
}
