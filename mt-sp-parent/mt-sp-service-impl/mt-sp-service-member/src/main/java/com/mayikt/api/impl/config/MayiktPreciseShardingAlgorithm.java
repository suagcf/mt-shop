package com.mayikt.api.impl.config;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class MayiktPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    /**
     * 真实建议是为500万测试为5条
     */
    private Long tableSize = 5l;

    /**
     * 插入数据 改写表的名称
     * 查询 改写表的名称
     * @param collection
     * @param preciseShardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        Double userId = Double.valueOf(preciseShardingValue.getValue());
        Double temp = userId / tableSize;
        String tableName = "meite_user" + (int) Math.ceil(temp);
        // 分表分库 userid====mysql自带的自增 序列 雪花算法
        log.info("<tableName{}>", tableName);
        return tableName;
    }
}
