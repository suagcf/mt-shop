package com.mayikt.api.pay.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("payment_channel")
public class PaymentChannelEntity {
    /**
     * ID
     */
    @Id
    @TableId
    private Integer id;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 渠道ID
     */
    private String channelId;
    /**
     * 商户id
     */
    private String merchantId;
    /**
     * 同步回调URL
     */
    private String syncUrl;
    /**
     * 异步回调URL
     */
    private String asynUrl;
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 渠道状态;0开启1关闭
     */
    @TableLogic
    private Integer is_delete;
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
     * payBeanId
     */
    private String payBeanId;


    private String synCallbackBeanId;
    private String asynCallbackBeanId;
}