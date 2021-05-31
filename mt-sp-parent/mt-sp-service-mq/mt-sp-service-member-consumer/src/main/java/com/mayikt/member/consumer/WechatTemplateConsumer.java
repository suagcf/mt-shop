package com.mayikt.member.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.weixin.dto.req.LoginReminderReqDto;
import com.mayikt.member.consumer.feign.WechatTemplateServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 微信模板消费者
 */
@Slf4j
@Component
@RabbitListener(queues = "fanout_wechattemplate_queue")
public class WechatTemplateConsumer {
    @Autowired
    private WechatTemplateServiceFeign wechatTemplateServiceFeign;

    @RabbitHandler
    public void process(String msg) {
        log.info("微信模板消费者 获取到msg：{}", msg);
        // feign 客户端技术 调用微信接口
        LoginReminderReqDto loginReminderReqDto = JSONObject.parseObject(msg, LoginReminderReqDto.class);
        BaseResponse<String> stringBaseResponse = wechatTemplateServiceFeign.sendTemplate(loginReminderReqDto);
        log.info("stringBaseResponse:{}", stringBaseResponse);
    }
}