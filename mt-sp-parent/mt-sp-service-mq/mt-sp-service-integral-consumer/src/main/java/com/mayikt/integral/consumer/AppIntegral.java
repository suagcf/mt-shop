package com.mayikt.integral.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mayikt.integral.consumer.mapper")
public interface AppIntegral {
    public static void main(String[] args) {
        SpringApplication.run(AppIntegral.class);
    }
}
