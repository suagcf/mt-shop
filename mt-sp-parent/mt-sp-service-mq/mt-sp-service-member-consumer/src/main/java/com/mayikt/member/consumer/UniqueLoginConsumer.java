package com.mayikt.member.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 唯一登录消费者
 */
@Slf4j
@Component
@RabbitListener(queues = "fanout_uniquelogin_queue")
public class UniqueLoginConsumer {
    @RabbitHandler
    public void process(String msg) {
        log.info("唯一登录消费者 获取到msg：{}", msg);
    }
}