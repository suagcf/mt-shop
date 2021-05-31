package com.mayikt.api.pay.strategy.impl;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.mayikt.api.pay.config.alipay.AlipayConfig;
import com.mayikt.api.pay.entity.PaymentChannelEntity;
import com.mayikt.api.pay.entity.PaymentInfoEntity;
import com.mayikt.api.pay.strategy.PayStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Component
@Slf4j
public class AliPayStrategy implements PayStrategy {
    @Override
    public String payToHtml(PaymentInfoEntity paymentInfoEntity, PaymentChannelEntity paymentChannelEntity) throws UnsupportedEncodingException, AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(paymentChannelEntity.getSyncUrl());
        alipayRequest.setNotifyUrl(paymentChannelEntity.getAsynUrl());
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = paymentInfoEntity.getOrderId();
        //付款金额，必填
        String total_amount = paymentInfoEntity.getPayAmount() + "";
        //订单名称，必填
        String subject = paymentInfoEntity.getOrderName();
        //商品描述，可空
        String body = paymentInfoEntity.getOrderBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}"
        );
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }
}
