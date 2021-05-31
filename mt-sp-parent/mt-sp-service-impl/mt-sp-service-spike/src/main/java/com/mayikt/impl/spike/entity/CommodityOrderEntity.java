package com.mayikt.impl.spike.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 余胜军
 * @ClassName CommodityOrderEntity
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年05月06日 18:40:00
 */
@Data
@TableName("commodity_order")
public class CommodityOrderEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long specsId;
    private Long userId;
    private BigDecimal payAmount;
    private Integer orderStatus;

    public CommodityOrderEntity(Long specsId, Long userId, BigDecimal payAmount, Integer orderStatus) {
        this.specsId = specsId;
        this.userId = userId;
        this.payAmount = payAmount;
        this.orderStatus = orderStatus;
    }
}
