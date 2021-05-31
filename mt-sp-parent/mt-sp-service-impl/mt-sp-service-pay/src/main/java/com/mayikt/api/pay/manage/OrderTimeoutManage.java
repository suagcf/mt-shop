package com.mayikt.api.pay.manage;

import com.mayikt.api.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 余胜军
 * @ClassName OrderTimeoutManage
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月22日 21:03:00
 */
@Component
@Slf4j
public class OrderTimeoutManage {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 订单交换机
     */
    @Value("${mayikt.order.exchange}")
    private String orderExchange;
    /**
     * 订单路由key
     */
    @Value("${mayikt.order.routingKey}")
    private String orderRoutingKey;
    @Autowired
    private RedisUtil redisUtil;


    public void sendOrderTimeoutMsg(String payId) {
        log.info(">下单成功 payId:{}<", payId);
//        rabbitTemplate.convertAndSend(orderExchange, orderRoutingKey, payId, message -> {
//            // 设置消息过期时间 10秒过期 改为常量
//            message.getMessageProperties().setExpiration("10000");
//            return message;
//        });
        redisUtil.setString(payId, payId, 10l);
    }
}
