package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo4.entity.CommunityUser;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CommunityUserMapper extends BaseMapper<CommunityUser> {
}
