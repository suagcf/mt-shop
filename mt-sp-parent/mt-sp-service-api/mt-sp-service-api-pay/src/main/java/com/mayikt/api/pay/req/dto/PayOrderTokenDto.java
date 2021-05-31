package com.mayikt.api.pay.req.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayOrderTokenDto {
	/**
	 * 支付金额
	 */
	private BigDecimal payAmount;
	/**
	 * 订单号码
	 */
	private String orderId;

	/**
	 * userId
	 */
	private Long userId;
	/**
	 * 订单名称
	 */
	private String orderName;
}
