package com.mayikt.api.impl.producer;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.impl.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class LoginProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 登录之后 后续异步处理的代码
     */
    public void sendMsgLoginFollowUp(Long userId, String loginIp, Date loginTime, String loginToken,
                                     String channel, String equipment, String wxOpenId, String phone) {
        JSONObject data = new JSONObject();
        data.put("userId", userId);
        data.put("loginIp", loginIp);
        data.put("loginTime", loginTime);
        data.put("loginToken", loginToken);
        data.put("channel", channel);
        data.put("equipment", equipment);
        data.put("openId", wxOpenId);
        data.put("phone", phone);
        // 使用rabbitmq投递消息
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAYIKT_NAME, "", data.toJSONString());
        log.info(">>>会员服务登录后续，投递消息到mq成功.<<<");
    }
}
