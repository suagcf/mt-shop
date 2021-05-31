package com.mayikt.canal.commodity.consumer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.mayikt.canal.commodity.constant.MayiktConstant;
import com.mayikt.canal.commodity.es.bean.CommodityInfo;
import com.mayikt.canal.commodity.es.repository.CommodityInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author 余胜军
 * @ClassName CanalConsumer
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月27日 19:14:00
 */
@Component
@Slf4j
public class CanalConsumer {
    @Autowired
    private CommodityInfoRepository commodityInfoRepository;

    /**
     * 消费者监听mayikt-topic
     *
     * @param consumer
     */
    @KafkaListener(topics = "mayikt-20212-topic")
    public void receive01(ConsumerRecord<?, ?> consumer) {
        log.info("分组1的消费者1>topic名称:{},,key:{},分区位置:{},offset{},数据:{}<",
                consumer.topic(), consumer.key(), consumer.partition(), consumer.offset(), consumer.value());
        String json = (String) consumer.value();
        JSONObject jsonObject = JSONObject.parseObject(json);
        String type = jsonObject.getString("type");

        String pkNames = jsonObject.getJSONArray("pkNames").getString(0);
        JSONArray data = jsonObject.getJSONArray("data");

        String table = jsonObject.getString("table");
        String database = jsonObject.getString("database");
        for (int i = 0; i < data.size(); i++) {
            JSONObject dataObject = data.getJSONObject(i);
            CommodityInfo productEntity = dataObject.toJavaObject(CommodityInfo.class);
            switch (type) {
                case MayiktConstant.CANAL_UPDATE:
                case MayiktConstant.CANAL_INSERT:
                    commodityInfoRepository.save(productEntity);
                    break;
                case MayiktConstant.CANAL_DELETE:
                    commodityInfoRepository.delete(productEntity);
                    break;
            }
        }
    }
}
