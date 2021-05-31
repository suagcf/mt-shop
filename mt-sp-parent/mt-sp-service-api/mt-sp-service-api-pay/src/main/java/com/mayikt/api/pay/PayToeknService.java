package com.mayikt.api.pay;


import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.pay.req.dto.PayOrderTokenDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PayToeknService {
    /**
     * 调用该接口 返回支付令牌
     * @return
     */
    @PostMapping("toPayResultToken")
    BaseResponse<String> toPayResultToken(@RequestBody PayOrderTokenDto payOrderTokenDto);
}
