package com.example.demo4.provider;

import com.example.demo4.entity.CommunityUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthorizeMethod {
    /**
     * 持久化登录，通过查询浏览器cookies中是否存在本站相关的cookies，存在则直接登录，否则提示登录
     * @param httpServletRequest
     * @return
     */
    public CommunityUser AutoMethod(HttpServletRequest httpServletRequest);

    public Boolean userLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
