package com.mayikt.elk.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余胜军
 * @ClassName AppLog
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月24日 17:25:00
 */
@SpringBootApplication
public class AppLog {
    @KafkaListener(topics = "mayikt-meite-topic")
    public void test(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppLog.class);
    }
}
