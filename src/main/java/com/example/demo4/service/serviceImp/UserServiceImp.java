package com.example.demo4.service.serviceImp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo4.entity.CommunityUser;
import com.example.demo4.mapper.CommunityUserMapper;
import com.example.demo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName storeUserImp
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/17 16:46
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private CommunityUserMapper communityUserMapper;

    @Override
    public Integer addUser(CommunityUser communityUser) {
        return communityUserMapper.insert(communityUser);
    }

    @Override
    public CommunityUser selectUserByCookie(String cookie) {
        QueryWrapper<CommunityUser> queryWrapper = new QueryWrapper<CommunityUser>();
        queryWrapper.eq("token",cookie);
        return communityUserMapper.selectOne(queryWrapper);
    }
}
