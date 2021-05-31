package com.mayikt.member.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 记录登录日志的消费者
 */
@Slf4j
@Component
@RabbitListener(queues = "fanout_log_queue")
public class LoginConsumer {
    @RabbitHandler
    public void process(String msg) {
        log.info("记录登录日志的消费者 获取到msg：{}", msg);
    }
}