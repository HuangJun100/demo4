package com.example.demo4.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo4.dto.QuestionDto;
import com.example.demo4.provider.AuthorizeMethod;
import com.example.demo4.service.QuestionService;
import com.example.demo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName indexController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/15 14:11
 */

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizeMethod authorizeMethod;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest, Model model){
        authorizeMethod.AutoMethod(httpServletRequest);
        IPage<QuestionDto> iPage = questionService.getQuestionList(new Page(0, 10));
        model.addAttribute("pages",iPage);
        return "index";
    }

    @GetMapping("/page")
    public String getPage(@RequestParam(name = "pageNum") Integer pageNum,
                           Model model){
        IPage<QuestionDto> iPage = questionService.getQuestionList(new Page(pageNum, 10));
        model.addAttribute("pages",iPage);
        return "index";
    }


    @GetMapping("/logout")
    public String indexLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
            authorizeMethod.userLogout(httpServletRequest,httpServletResponse);
        return "redirect:/";
    }
}
