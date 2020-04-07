package com.example.demo4.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionDto{
    /**
     *  问题的主键
     */
    private long id;
    /**
     * 用户头像地址
     */
    private String avatarUrl;
    /**
     * 作者的名字
     */
    private String githubName;
    /**
     * 问题的标题
     */
    private String title;
    /**
     * 问题的主体
     */
    private  String question;
    /**
     * 问题的标签
     */
    private String tag;
    /**
     * 作者id
     */
    private Long author;
    /**
     * 点赞数
     */
    private Long likeCount;
    /**
     * 阅读数
     */
    private Long readCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
