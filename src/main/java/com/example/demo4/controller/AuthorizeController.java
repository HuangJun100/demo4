package com.example.demo4.controller;

import com.example.demo4.dto.AccessTokenDTO;
import com.example.demo4.dto.GithubUser;
import com.example.demo4.entity.CommunityUser;
import com.example.demo4.provider.GithubProvider;
import com.example.demo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName AuthorizeController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/16 14:11
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.clientId}")
    private String clientId;
    @Value("${github.clientSecret}")
    private String clientSecret;
    @Value("${github.redirectUrl}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name ="state") String state,
                           HttpServletResponse httpServletResponse){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(
                clientId,clientSecret,code,redirectUrl,state);

        String accessToken = githubProvider.GetAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.GetUserByToken(accessToken);

        Long userId = githubUser.getId();
        if (userId != null) {
            //登陆成功
            /**
             * 生成UUID cookie
             */
            String communityCookie = UUID.randomUUID().toString();

            CommunityUser communityUser = new CommunityUser();
            communityUser.setGithubBio(githubUser.getBio());
            communityUser.setGithubId(githubUser.getId());
            communityUser.setGithubName(githubUser.getName());
            communityUser.setToken(communityCookie);
            communityUser.setCreateTime(new Date());
            userService.addUser(communityUser);
            /**
             * 生成指定的cookie
             */
            httpServletResponse.addCookie(new Cookie("userCookie",communityCookie));
           // httpServletRequest.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }
}
