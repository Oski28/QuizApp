package com.example.projectwebjava.controller;

import com.example.projectwebjava.exception.AnswerNullTextException;
import com.example.projectwebjava.exception.AnswersSizeException;
import com.example.projectwebjava.model.Question;
import com.example.projectwebjava.service.QuestionService;
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
@RequestMapping("/question")
public class QuestionController {

    private final Integer PAGE_SIZE = 10;

    private String editQuestionSourceUrl;

    private QuestionService questionService;


    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String getQuestions(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional <Integer>size) {
        model.addAttribute("question", questionService.getEmptyQuestion());
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(PAGE_SIZE);

        Page<Question> questionPage = questionService.getQuestionsPage(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("questions", questionPage);

        int totalPages = questionPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "questions";
    }

    @GetMapping("edit/{id}")
    public String editQuestion(@PathVariable Long id, @RequestParam("url") String url, Model model) {
        editQuestionSourceUrl = url;
        model.addAttribute("returnUrl", editQuestionSourceUrl);
        model.addAttribute("question", questionService.resizeAnswerList(questionService.getQuestionById(id)));
        return "editQuestion";
    }

    @PostMapping
    public String saveQuestion(@Valid @ModelAttribute("question") Question question,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            Page<Question> questionPage = questionService.getQuestionsPage(PageRequest.of(0, PAGE_SIZE));
            model.addAttribute("questions", questionPage);

            int totalPages = questionPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            return "questions";
        }
        try {
            questionService.addQuestion(question);
        } catch (AnswersSizeException e) {
            return "redirect:/question?error";
        }
        return "redirect:/question";
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.removeQuestionFromQuizzes(questionService.getQuestionById(id));
        questionService.deleteQuestion(id);
        return "redirect:/question";
    }

    @PutMapping("edit/{id}")
    public String updateQuestion(@Valid @ModelAttribute("question") Question question,
                                 BindingResult bindingResult,
                                 @PathVariable Long id,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("returnUrl", editQuestionSourceUrl);
            return "\\partials\\editQuestion.html";
        }
        try {
            questionService.updateQuestion(question);
        } catch (AnswersSizeException | AnswerNullTextException e) {
            return "redirect:/question/edit/{id}?url=" + editQuestionSourceUrl + "&error";
        }
        return "redirect:" + editQuestionSourceUrl;
    }
}
