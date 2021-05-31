package com.mayikt.api.spike;

import com.mayikt.api.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 余胜军
 * @ClassName SpikeService
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年05月06日 15:55:00
 */
public interface SpikeService {
    /**
     * @param userId
     * @param commodityId
     * @return
     */
    @GetMapping("/spike")
    BaseResponse<String> spike(Long userId, Long commodityId);

}
