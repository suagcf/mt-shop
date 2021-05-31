package com.mayikt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 余胜军
 * @ClassName AppSpike
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年05月06日 16:31:00
 */
@SpringBootApplication
@MapperScan("com.mayikt.impl.spike.mapper")
public class AppSpike {
    public static void main(String[] args) {
        SpringApplication.run(AppSpike.class);
    }
}
