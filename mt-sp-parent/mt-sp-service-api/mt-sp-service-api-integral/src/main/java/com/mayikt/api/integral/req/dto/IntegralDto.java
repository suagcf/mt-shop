package com.mayikt.api.integral.req.dto;

import lombok.Data;

/**
 * @author 余胜军
 * @ClassName IntegralDto
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月17日 21:28:00
 */
@Data
public class IntegralDto {
    private Long value;
    private Long userId;
    private String orderId;

    public IntegralDto(Long value, Long userId, String orderId) {
        this.value = value;
        this.userId = userId;
        this.orderId = orderId;
    }

    public IntegralDto() {
        this.value = value;
    }
}
