package com.mayikt.api.pay.mq;


import com.mayikt.api.pay.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerIntegral {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsg(String msg) {
        // 使用rabbitmq投递消息
        //消息消费者确认收到消息后，手动ack回执
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAYIKT_NAME, "", msg);
    }
}
