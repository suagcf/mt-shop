package com.mayikt.api.integral.impl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 余胜军
 * @ClassName AppIntegral
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月17日 21:38:00
 */
@SpringBootApplication
@MapperScan("com.mayikt.api.integral.impl.mapper")
public class AppIntegral {
    public static void main(String[] args) {
        SpringApplication.run(AppIntegral.class);
    }
}
