package com.mayikt.impl.spike.manager;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class SpikeManager {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderMsg(JSONObject data) {
        kafkaTemplate.send("mayikt-topic-spike", null, data.toJSONString());
    }

    @Async
    public void sendSpikeMsg(Long userId, Long seckillId, String spikeToken) {
        JSONObject data = new JSONObject();
        data.put("userId", userId);
        data.put("seckillId", seckillId);
        data.put("spikeToken", spikeToken);
        // 投递消息到MQ中
        sendOrderMsg(data);
    }
}
