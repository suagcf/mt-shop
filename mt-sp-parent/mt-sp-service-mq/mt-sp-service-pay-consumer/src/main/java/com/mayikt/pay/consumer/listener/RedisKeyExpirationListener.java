package com.mayikt.pay.consumer.listener;

import com.mayikt.pay.consumer.service.PayOrderTimeoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private PayOrderTimeoutService payOrderTimeoutService;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // redis 客户端监听 Redis 16库 每个库对应不同的业务逻辑 前缀 order_timeOut_支付id
        String key = message.toString();
        log.info(">key:{}已经过期!<", key);
        Long payId = Long.parseLong(key);
        payOrderTimeoutService.orderTimeout(payId);
    }
}