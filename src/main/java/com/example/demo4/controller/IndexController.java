package com.example.demo4.controller;

import com.example.demo4.entity.CommunityUser;
import com.example.demo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userCookie")){
                String thisCookie = cookie.getValue();
                CommunityUser communityUser = userService.selectUserByCookie(thisCookie);
                if(communityUser != null){
                    httpServletRequest.getSession().setAttribute("user",communityUser);
                }
                break;
            }
        }
        return "index";
    }
}
