package com.mayikt.api.pay;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.base.BaseApiService;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.pay.config.ContextUtils;
import com.mayikt.api.pay.entity.PaymentChannelEntity;
import com.mayikt.api.pay.entity.PaymentInfoEntity;
import com.mayikt.api.pay.mapper.PaymentChannelMapper;
import com.mayikt.api.pay.mapper.PaymentInfoMapper;
import com.mayikt.api.pay.strategy.PayStrategy;
import com.mayikt.api.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PayInfoServiceImpl extends BaseApiService<String> implements PayInfoService {
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;
    @Autowired
    private PaymentChannelMapper paymentChannelMapper;

    @Override
    public BaseResponse<String> payToHtml(String payToken, String channelId) {
        if (StringUtils.isEmpty(payToken)) {
            return setResultError("payToken is null");
        }
        String payInfoId = tokenUtils.getTokenValue(payToken);
        if (StringUtils.isEmpty(payInfoId)) {
            return setResultError("pay token 已经过期!");
        }
        PaymentInfoEntity paymentInfoEntity = paymentInfoMapper.selectById(payInfoId);
        if (paymentInfoEntity == null) {
            return setResultError("该支付payToken错误!");
        }
        // 使用策略模式执行不同的支付渠道
        QueryWrapper<PaymentChannelEntity> paymentChannelEntity = new QueryWrapper<>();
        paymentChannelEntity.eq("CHANNEL_ID", channelId);
        PaymentChannelEntity dbPaymentChannel = paymentChannelMapper.selectOne(paymentChannelEntity);
        if (dbPaymentChannel == null) {
            return setResultError("该渠道已经关闭或者没有配置!");
        }
        String payBeanId = dbPaymentChannel.getPayBeanId();
        PayStrategy payStrategy = ContextUtils.getBean(payBeanId, PayStrategy.class);
        try {
            String result = payStrategy.payToHtml(paymentInfoEntity,dbPaymentChannel);
            log.info(">>>result:{}<<<", result);
            return setResultSuccess(result);
        } catch (Exception e) {
            log.error(">>e:{}<<", e);
            return setResultError("系统错误");
        }


    }
}
