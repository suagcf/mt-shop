package com.mayikt.api.pay.strategy;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.alipay.api.AlipayApiException;
import com.mayikt.api.pay.entity.PaymentChannelEntity;
import com.mayikt.api.pay.entity.PaymentInfoEntity;

import java.io.UnsupportedEncodingException;

public interface PayStrategy {
    String payToHtml(PaymentInfoEntity paymentInfoEntity, PaymentChannelEntity paymentChannelEntity) throws UnsupportedEncodingException, AlipayApiException;
}
