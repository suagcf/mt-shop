package com.mayikt.pay.consumer.config;

import com.mayikt.pay.consumer.listener.RedisKeyExpirationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author 余胜军
 * @ClassName RedisConfig
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月22日 18:32:00
 */
@Configuration
public class RedisConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new RedisKeyExpirationListener(container), new PatternTopic("__keyevent@0__:expired"));
        return container;
    }
}