package com.mayikt.api.impl.config;

import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;


/**
 * RabbitmqConfig
 */
@Component
public class RabbitMQConfig {
    /**
     * 定义交换机
     */
    public static String EXCHANGE_MAYIKT_NAME = "/mayikt_ex";


    /**
     * 日志队列
     */
    private String MAYIKT_LOG_QUEUE = "fanout_log_queue";

    /**
     * 唯一登录队列
     */
    private String MAYIKT_UNIQUELOGIN_QUEUE = "fanout_uniquelogin_queue";


    /**
     * 微信模板队列
     */
    private String MAYIKT_WECHAT_TEMPLATE_QUEUE = "fanout_wechattemplate_queue";

    /**
     * 配置fanout_log_queue
     *
     * @return
     */
    @Bean
    public Queue fanoutLogQueue() {
        return new Queue(MAYIKT_LOG_QUEUE);
    }

    /**
     * 配置MAYIKT_UNIQUELOGIN_QUEUE
     *
     * @return
     */
    @Bean
    public Queue fanoutUniqueloginQueue() {
        return new Queue(MAYIKT_UNIQUELOGIN_QUEUE);
    }
    /**
     * 配置MAYIKT_UNIQUELOGIN_QUEUE
     *
     * @return
     */
    @Bean
    public Queue fanoutWechattemplateQueue() {
        return new Queue(MAYIKT_WECHAT_TEMPLATE_QUEUE);
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

    // 绑定交换机 Log
    @Bean
    public Binding bindingLogFanoutExchange(Queue fanoutLogQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutLogQueue).to(fanoutExchange);
    }
    // 绑定交换机 fanoutUniqueloginQueue
    @Bean
    public Binding bindingUniqueLogFanoutExchange(Queue fanoutUniqueloginQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutUniqueloginQueue).to(fanoutExchange);
    }

    // 绑定交换机 Wechattemplate
    @Bean
    public Binding bindingWechattemplateFanoutExchange(Queue fanoutWechattemplateQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutWechattemplateQueue).to(fanoutExchange);
    }


}