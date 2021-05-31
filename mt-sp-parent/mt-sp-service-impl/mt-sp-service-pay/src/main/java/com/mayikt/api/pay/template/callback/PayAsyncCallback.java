package com.mayikt.api.pay.template.callback;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.pay.config.ContextUtils;
import com.mayikt.api.pay.entity.PaymentChannelEntity;
import com.mayikt.api.pay.mapper.PaymentChannelMapper;
import com.mayikt.api.pay.template.AbstractPayCallbackTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayAsyncCallback {
    @Autowired
    private PaymentChannelMapper paymentChannelMapper;

    @PostMapping("/asyncCallback")
    public String asyncCallback(String channelId) {
        if (StringUtils.isEmpty(channelId)) {
            return "error";
        }
        QueryWrapper<PaymentChannelEntity> paymentChannelEntity = new QueryWrapper<>();
        paymentChannelEntity.eq("CHANNEL_ID", channelId);
        PaymentChannelEntity dbPaymentChannel = paymentChannelMapper.selectOne(paymentChannelEntity);
        if (dbPaymentChannel == null) {
            return "error";
        }
        AbstractPayCallbackTemplate aliPayCallbackTemplate =
                ContextUtils.getBean(dbPaymentChannel.getAsynCallbackBeanId(), AbstractPayCallbackTemplate.class);
        return aliPayCallbackTemplate.asynCallback();
    }
}
