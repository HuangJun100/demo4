package com.example.demo4.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Question
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/18 16:14
 */
@Data
@TableName(value = "question")
public class Question {
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
