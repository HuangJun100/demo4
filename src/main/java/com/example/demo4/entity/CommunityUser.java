package com.example.demo4.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName CommunityUser
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/17 16:13
 */
@Data
@TableName(value="community_user")
public class CommunityUser {
    /**
     * 本站用户id
     */
    private Long id;
    /**
     * github用户ID
     */
    private Long githubId;
    /**
     * github用户ID
     */
    private String githubName;
    /**
     *github用户描述
     */
    private String githubBio;
    /**
     * 用户cookie
     */
    private String token;
    /**
     * 新增加时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
