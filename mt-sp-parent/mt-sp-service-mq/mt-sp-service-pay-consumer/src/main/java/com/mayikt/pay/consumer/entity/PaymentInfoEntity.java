package com.mayikt.pay.consumer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("payment_info")
public class PaymentInfoEntity {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 支付金额
     */
    private BigDecimal payAmount;
    /**
     * 支付状态;0待支付1已经支付2支付超时3支付失败
     */
    private Integer paymentStatus;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 订单号码
     */
    private String orderId;
    /**
     * 乐观锁
     */
    private Integer revision;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用
     */
    private String partyPayId;

    /**
     * 使用雪花算法生产 支付系统 支付id
     */
    private String paymentId;

    /**
     * 支付渠道
     */
    private String paymentChannel;

    /**
     * 支付描述
     */
    private String orderName;
    /**
     * 支付描述
     */
    private String orderBody;

}