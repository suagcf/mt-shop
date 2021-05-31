package com.mayikt.gateway;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppGateway {
    public static void main(String[] args) {
        //Gateway 底层基于 webflux编写的  需要去除 springboot-web组件
        SpringApplication.run(AppGateway.class);
    }
}
