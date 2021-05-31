package com.mayikt.impl.spike.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 余胜军
 * @ClassName CommoditySpecsMapper
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年05月06日 16:55:00
 */
@Data
@TableName("commodity_specs")
public class CommoditySpecsEntity {
    private Long id;
    private Long commodityId;
    private String commoditySpecs;
    private Long specsSeq;
    private Long commodityStok;
    private Long commodityPrice;
    private Date createTime;
    private Date updateTime;
}
