package com.mayikt.pay.consumer;

import com.mayikt.pay.consumer.service.PayOrderTimeoutService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 余胜军
 * @ClassName PayOrderTimeoutConsumer
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月22日 21:12:00
 */
@Component
public class PayOrderTimeoutConsumer {
    @Autowired
    private PayOrderTimeoutService payOrderTimeoutService;

    /**
     * 死信队列监听队列回调的方法
     *
     * @param msg
     */
    @RabbitListener(queues = "mayikt_order_dlx_queue")
    public void orderConsumer(String msg) {
        Long payId = Long.parseLong(msg);
        payOrderTimeoutService.orderTimeout(payId);
    }
}
