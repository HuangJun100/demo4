package com.example.demo4.provider.impl;

import com.example.demo4.entity.CommunityUser;
import com.example.demo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AuthorizeMethod
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/20 15:08
 */
@Service
public class AuthorizeMethod implements com.example.demo4.provider.AuthorizeMethod {

    @Autowired
    UserService userService;

    @Override
    public CommunityUser AutoMethod(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    String thisCookie = cookie.getValue();
                    CommunityUser communityUser = userService.selectUserByCookie(thisCookie);
                    if (communityUser != null) {
                        httpServletRequest.getSession().setAttribute("user", communityUser);
                        return communityUser;
                    }
                    break;
                }
            }
        }
        return null;
    }

    @Override
    public Boolean userLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if(AutoMethod(httpServletRequest)!=null){
            httpServletResponse.addCookie(new Cookie("userCookie",null));
            httpServletRequest.getSession().setAttribute("user", null);
        }
        return null;
    }
}
