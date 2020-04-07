package com.example.demo4.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.demo4.entity.CommunityUser;
import com.example.demo4.mapper.CommunityUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserLoginOrRegist {

    @Autowired
    private CommunityUserMapper communityUserMapper;

    @PostMapping("/userRegist")
    public String userRegist(@RequestParam(name="username") String userName,
                             @RequestParam(name="birthdate")String birthDate,
                             @RequestParam(name="email")String email,
                             @RequestParam(name="password") String passWord,
                             @RequestParam(name="repassword") String rePassWord,
                             Model model){
        model.addAttribute("username",userName);
        model.addAttribute("birthdate",birthDate);
        model.addAttribute("email",email);
        model.addAttribute("password",passWord);
        model.addAttribute("repassword",rePassWord);

        List list = new ArrayList();
        list.add(userName);
        list.add(birthDate);
        list.add(email);
        list.add(passWord);
        list.add(rePassWord);

        System.out.println(list);
        Long nullCount = list.stream().filter(s->s.equals(null)|s.equals(""))
                .count();
        Boolean fullMsg = true;
        if(nullCount>0){
            model.addAttribute("msgBackFull","请完整填写信息");
            fullMsg = false;
        }else if(nullCount==0){
            model.addAttribute("msgBackFull",null);
            fullMsg = true;
        }

        Boolean passwordMsg = true;
        if(!passWord.equals(rePassWord)){
            model.addAttribute("msgBackPassword","两次输入密码不一致");
            passwordMsg = false;
        }else if(passWord.equals(rePassWord)){
            model.addAttribute("msgBackPassword",null);
            passwordMsg = true;
        }

        CommunityUser communityUser2 = new LambdaQueryChainWrapper<CommunityUser>(communityUserMapper)
                .select(CommunityUser::getGithubName).eq(CommunityUser::getGithubName,"黄俊").select(CommunityUser::getGithubName).one();
        System.out.println(communityUser2);

        LambdaQueryWrapper<CommunityUser> queryWrapper  = new LambdaQueryWrapper<CommunityUser>();
        queryWrapper.select(CommunityUser::getGithubName).eq(CommunityUser::getGithubName,userName);
        CommunityUser communityUser1 = communityUserMapper.selectOne(queryWrapper);
        if(communityUser1!=null){
            model.addAttribute("msgUserRepeat","该用户名已存在");
            return "/userRegist";
        }else if (communityUser1==null && fullMsg && passwordMsg){
            model.addAttribute("msgUserRepeat",null);
            CommunityUser communityUser = new CommunityUser();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                communityUser.setBirthDate(sdf.parse(birthDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            communityUser.setGithubName(userName);
            communityUser.setCreateTime(new Date());
            communityUser.setEmail(email);
            communityUser.setPassword(passWord);
            String communityCookie = UUID.randomUUID().toString();
            communityUser.setToken(communityCookie);

            communityUserMapper.insert(communityUser);

            return "redirect:/";
        }else{
            return "/userRegist";
        }
    }

    @PostMapping("/userLogin")
    public String userLogin(@RequestParam(name="username") String userName,
                            @RequestParam(name="password") String password){

        return "redirect:/";
    }

    /**
     * 用户注册页面
     * @return
     */
    @GetMapping("/userRegist")
    public String userRegist(){
        return "/userRegist";
    }
    /**
     * 用户登录界面
     */
    @GetMapping("/login")
    public String userLogin(){return "login";}
}
