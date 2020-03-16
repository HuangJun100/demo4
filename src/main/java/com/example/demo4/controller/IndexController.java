package com.example.demo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName indexController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/15 14:11
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
