package com.mayikt.api.spike;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 余胜军
 * @ClassName OrderTokenService
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年05月06日 19:05:00
 */
public interface OrderTokenService {
    @GetMapping("/addSpikeToken")
    BaseResponse<JSONObject> addSpikeToken(Long seckillId);

}
