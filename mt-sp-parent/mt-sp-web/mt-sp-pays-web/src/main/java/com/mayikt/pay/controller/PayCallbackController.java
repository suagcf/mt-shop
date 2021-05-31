package com.mayikt.pay.controller;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.pay.config.ContextUtils;
import com.mayikt.pay.entity.PaymentChannelEntity;
import com.mayikt.pay.mapper.PaymentChannelMapper;
import com.mayikt.pay.strategy.SynCallbackStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayCallbackController {
    @Autowired
    private PaymentChannelMapper paymentChannelMapper;

    /**
     * 同步回调   支付宝 平安 银联支付
     *
     * @return
     */
    @RequestMapping("/synchronousCallback")
    public String synchronousCallback(String channelId) {
        if (StringUtils.isEmpty(channelId)) {
            return "error";
        }
        // 执行同步策略类
        // 使用策略模式执行不同的支付渠道
        QueryWrapper<PaymentChannelEntity> paymentChannelEntity = new QueryWrapper<>();
        paymentChannelEntity.eq("CHANNEL_ID", channelId);
        PaymentChannelEntity dbPaymentChannel = paymentChannelMapper.selectOne(paymentChannelEntity);
        if (dbPaymentChannel == null) {
            return "error";
        }
        String synCallbackBeanId = dbPaymentChannel.getSynCallbackBeanId();
        SynCallbackStrategy synCallbackStrategy = ContextUtils.getBean(synCallbackBeanId, SynCallbackStrategy.class);
        String result = synCallbackStrategy.synchronousCallback();
        return result;
    }

}
