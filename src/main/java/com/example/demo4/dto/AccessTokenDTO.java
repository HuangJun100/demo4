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

}
