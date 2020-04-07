package com.example.demo4.dto;

import lombok.Data;

/**
 * @ClassName GithubUser
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/16 15:11
 */
@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    private String avatarUrl;
}
