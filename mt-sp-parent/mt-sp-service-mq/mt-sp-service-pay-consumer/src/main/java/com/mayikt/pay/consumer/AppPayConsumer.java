package com.mayikt.pay.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 余胜军
 * @ClassName AppPayConsumer
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月22日 21:25:00
 */
@SpringBootApplication
@MapperScan("com.mayikt.pay.consumer.mapper")
public class AppPayConsumer {
    public static void main(String[] args) {
        SpringApplication.run(AppPayConsumer.class);
    }
}
