package com.mayikt.api.pay.feign;

import com.mayikt.api.integral.IntegralService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 余胜军
 * @ClassName IntegralServiceFeign
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月17日 21:34:00
 */
@FeignClient("mayikt-integral")
public interface IntegralServiceFeign extends IntegralService {
}
