package com.mayikt.api.pay.template;

import com.mayikt.api.pay.entity.PaymentInfoEntity;
import lombok.Data;

@Data
public class AsynCallbackEntity {
    private Boolean result;
    private PaymentInfoEntity paymentInfoEntity;

    public AsynCallbackEntity(boolean result, PaymentInfoEntity paymentInfoEntity) {
        this.result = result;
        this.paymentInfoEntity = paymentInfoEntity;
    }
}
