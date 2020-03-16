package com.example.demo4.controller;

import com.example.demo4.dto.AccessTokenDTO;
import com.example.demo4.dto.GithubUser;
import com.example.demo4.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @Value("${github.clientId}")
    private String clientId;
    @Value("${github.clientSecret}")
    private String clientSecret;
    @Value("${github.redirectUrl}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name ="state") String state,
                           HttpServletRequest httpServletRequest){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.GetAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.GetUserByToken(accessToken);
        Long userId = githubUser.getId();
        if (userId != null) {
            //登陆成功
            httpServletRequest.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }
}
