package com.example.demo4.service;

import com.example.demo4.entity.CommunityUser;


public interface UserService {

    /**
     * 将获得的用户数据存储
     */
    public Integer addUser(CommunityUser communityUser);
    /**
     * 通过cookie查询用户
     */
    public CommunityUser selectUserByCookie(String cookie);

    /**
     * 通过ID查询用户
     * @param id
     * @return
     */
    public CommunityUser selectUserByGithubId(Long id);
}
