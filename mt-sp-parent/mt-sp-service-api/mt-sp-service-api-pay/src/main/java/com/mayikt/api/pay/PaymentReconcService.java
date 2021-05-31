package com.mayikt.api.pay;

import com.mayikt.api.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 余胜军
 * @ClassName PaymentReconcService
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月17日 21:02:00
 */
public interface PaymentReconcService {
    /**
     * 根据商户订单号码实现补偿
     *
     * @return
     */
    @GetMapping("payReconciliation")
    BaseResponse<String> payReconciliation(String orderId);
}
