package com.example.demo4.dto;

import lombok.Data;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * @ClassName AccessTokenDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/16 14:37
 */
@Data
public class AccessTokenDTO{
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;

    public AccessTokenDTO() {
    }

    public AccessTokenDTO(String client_id, String client_secret, String code, String redirect_url, String state) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_url = redirect_url;
        this.state = state;
    }
}
