package com.mayikt.api.impl.feign;

import com.mayikt.api.weixin.WeChatService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("mayikt-weixin")
public interface WeChatServiceFeign extends WeChatService {

//    /**
//     * feign rpc远程调用 405
//     * @param a
//     * @return
//     */
//    @GetMapping("/getWeChat")
//    String getWeChat( @RequestParam("a")Integer a);
}