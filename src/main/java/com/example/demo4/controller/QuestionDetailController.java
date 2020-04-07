package com.example.demo4.controller;

import com.example.demo4.dto.QuestionDto;
import com.example.demo4.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionDetailController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questiondetail")
    public String questionDetail(@RequestParam(name = "questionId") Long id,
                                 Model model){
        QuestionDto questionDto = questionService.getquestionById(id);
        model.addAttribute("question",questionDto);
        System.out.println(questionDto);
        return "questionDetail";

    }
}
