package com.mayikt;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mayikt.api.impl.mapper")
public class AppWeChat {
    public static void main(String[] args) {
        // 1.com.mayikt.api.impl.weixin
        // 2.com.mayikt.api.impl.feign
        // 3.com.mayikt.api.base
        SpringApplication.run(AppWeChat.class);
    }
}
