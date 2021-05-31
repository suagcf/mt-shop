package com.mayikt.impl.spike.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.impl.spike.entity.CommodityOrderEntity;
import com.mayikt.impl.spike.entity.CommoditySpecsEntity;
import com.mayikt.impl.spike.mapper.CommodityOrderMapprer;
import com.mayikt.impl.spike.mapper.CommoditySpecsMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InventoryConsumer {
    @Autowired
    private CommoditySpecsMapper commoditySpecsMapper;
    @Autowired
    private CommodityOrderMapprer commodityOrderMapprer;

    @KafkaListener(topics = "mayikt-topic-spike")
    public void receive(ConsumerRecord<?, ?> consumer) throws Exception {
        String json = (String) consumer.value();
        if (StringUtils.isEmpty(json)) {
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        Long userId = jsonObject.getLong("userId");
        Long seckillId = jsonObject.getLong("seckillId");
        // 幂等 userid+商品规格id+秒杀token 做唯一去重
        // 3.开始实现扣库存
        CommoditySpecsEntity commoditySpecsEntity = commoditySpecsMapper.selectById(seckillId);
        if (commoditySpecsEntity == null) {
//            return setResultError("未查找到该商品内容");
        }
        Long commodityStok = commoditySpecsEntity.getCommodityStok();
        if (commodityStok <= 0) {
//            return setResultError("未查找到该商品内容");
        }
        int result = commoditySpecsMapper.deductionInventory(seckillId);
        if (result <= 0) {
//            return setResultError("秒杀失败，库存已经没有啦!");
        }
        // 4.插入订单
        CommodityOrderEntity commodityOrderEntity = new CommodityOrderEntity(seckillId, userId, new BigDecimal(99), 0);
        commodityOrderMapprer.insert(commodityOrderEntity);
    }
}
