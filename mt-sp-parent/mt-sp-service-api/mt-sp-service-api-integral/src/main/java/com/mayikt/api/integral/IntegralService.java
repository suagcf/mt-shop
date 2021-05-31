package com.mayikt.api.integral;

import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.integral.req.dto.IntegralDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 余胜军
 * @ClassName IntegralService
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月17日 17:03:00
 */
public interface IntegralService {
    /**
     * 增加积分接口
     *
     * @return
     */
    @PostMapping("/addIntegral")
    BaseResponse<String> addIntegral(@RequestBody  IntegralDto integralDto);
}