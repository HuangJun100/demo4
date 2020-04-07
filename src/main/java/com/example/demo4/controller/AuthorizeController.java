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
        /**
         * 获取GitHub授权登录
         */
        String accessToken = githubProvider.GetAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.GetUserByToken(accessToken);

        System.out.println(githubUser);
        /**
         * 获取授权成功后，和本地数据对比后存在就直接登录，否则存入数据库后登陆
         */
        if (githubUser != null) {
            Long githubId = githubUser.getId();
            CommunityUser communityUser0 = userService.selectUserByGithubId(githubId);
            String communityCookie;
            if(communityUser0==null) {
                //登陆成功
                /**
                 * 生成UUID cookie
                 */

                CommunityUser communityUser = new CommunityUser();
                communityUser.setGithubId(githubUser.getId());
                communityUser.setGithubName(githubUser.getName());
                communityUser.setGithubBio(githubUser.getBio());
                communityUser.setAvatarUrl(githubUser.getAvatarUrl());
                communityCookie = UUID.randomUUID().toString();
                communityUser.setToken(communityCookie);
                communityUser.setCreateTime(new Date());
                userService.addUser(communityUser);
            }else {
                communityCookie = communityUser0.getToken();
            }
            /**
             * 将cookie传给浏览器
             */
            httpServletResponse.addCookie(new Cookie("userCookie",communityCookie));

            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }
}
