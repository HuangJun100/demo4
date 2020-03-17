package com.example.demo4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo4.Mapper")
public class Demo4Application {

	public static final String currentVersion = "V1";

	public static void main(String[] args) {
		SpringApplication.run(Demo4Application.class, args);
	}

}
