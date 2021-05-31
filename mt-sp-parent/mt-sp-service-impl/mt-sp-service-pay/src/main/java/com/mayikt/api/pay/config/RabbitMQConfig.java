package com.mayikt.api.pay.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {

    public static String EXCHANGE_MAYIKT_NAME = "/mayikt_ex";


    private String INTEGRAL_QUEUE = "integral_queue";


    @Bean
    public Queue fanoutIntegralQueue() {
        return new Queue(INTEGRAL_QUEUE);
    }

    /**
     * 配置fanoutExchange
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_MAYIKT_NAME);
    }


    // 绑定交换机
    @Bean
    public Binding bindingIntegralFanoutExchange(Queue fanoutIntegralQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutIntegralQueue).to(fanoutExchange);
    }
}
