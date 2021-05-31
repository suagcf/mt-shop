package com.mayikt.api.integral.impl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("integral_info")
public class IntegralInfoEntity {
    @TableId(type= IdType.AUTO)
    private Long id;
    private Long value;
    private Long userId;
    private String orderId;
    @TableLogic
    private Integer deleted;
    private Date createTime;
    private Date updateTime;
}