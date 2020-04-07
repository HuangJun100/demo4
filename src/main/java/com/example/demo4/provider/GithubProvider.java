package com.example.demo4.provider;

import com.alibaba.fastjson.JSON;
import com.example.demo4.dto.AccessTokenDTO;
import com.example.demo4.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName GithubProvider
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/16 14:36
 */
@Service
public class GithubProvider {
    /**
     * 根据返回的code和state访问github接口，获取github的token
     * @param accessTokenDTO
     * @return
     */
    public String GetAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据token访问API获取用户信息
     * @param AccessToken
     * @return
     */
    public GithubUser GetUserByToken(String AccessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+AccessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            System.out.println(string);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
