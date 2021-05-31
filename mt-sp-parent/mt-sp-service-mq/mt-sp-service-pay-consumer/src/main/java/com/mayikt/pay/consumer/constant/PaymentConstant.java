package com.mayikt.pay.consumer.constant;

public interface PaymentConstant {
    //  支付成功
    Integer PAYMENT_STATUS_SUCCESS = 1;
    //  待支付
    Integer PAYMENT_STATUS_NOT = 0;
    // 支付超时
    Integer PAYMENT_STATUS_TIMEOUT = 2;
    Integer RPC_PAY_SUCCESS = 10000;
}