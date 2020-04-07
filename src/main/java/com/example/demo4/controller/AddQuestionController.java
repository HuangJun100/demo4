package com.example.demo4.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo4.entity.CommunityUser;
import com.example.demo4.entity.Question;
import com.example.demo4.provider.AuthorizeMethod;
import com.example.demo4.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName AddQuestionController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/18 14:20
 */
@Controller
public class AddQuestionController {

    @Autowired
    private AuthorizeMethod authorizeMethod;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/addQuestion")
    public String addQuestion(){
        return "addQuestion";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestParam(value = "title",required = false) String title,
                              @RequestParam(value ="question",required = false) String question,
                              @RequestParam(value ="tag",required = false) String tag,
                              HttpServletRequest httpServletRequest,
                              Model model){
        model.addAttribute("title",title);
        model.addAttribute("question",question);
        model.addAttribute("tag",tag);

        CommunityUser communityUser = authorizeMethod.AutoMethod(httpServletRequest);
        /**
         * 如果返回的用户为空，则返回用户为登陆。
         */
        model.addAttribute("error",null);
        if(communityUser == null){
            model.addAttribute("error","用户未登录");
            return "addQuestion";
        }

        /**
         * 如果有任何未填写的信息则返回提示。
         */
        if(title == null || title.equals("")|| title.equals("标题不能为空")){
            model.addAttribute("title","标题不能为空");
        }
        if(question == null || question.equals("")||question.equals("内容不能为空")){
            model.addAttribute("question","内容不能为空");
        }
        if(tag == null || tag.equals("")||tag.equals("标签不能为空")){
            model.addAttribute("tag","标签不能为空");
        }
        if(title == null || title.equals("") || question == null ||
                question.equals("") || tag == null || tag.equals("")
                ||title.equals("标题不能为空")||question.equals("内容不能为空")
                ||tag.equals("标签不能为空")){
            return "addQuestion";
        }else {
            Question question1 = new Question();
            question1.setAuthor(communityUser.getId());
            question1.setCreateTime(new Date());
            question1.setQuestion(question);
            question1.setTag(tag);
            question1.setTitle(title);

            Integer integer = questionService.addQuestion(question1);

            System.out.println(model+","+integer);
            System.out.println(question1);
            return "redirect:/";
        }

    }
}
