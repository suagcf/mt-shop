package com.mayikt.integral.consumer;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.integral.consumer.entity.IntegralInfoEntity;
import com.mayikt.integral.consumer.mapper.IntegralInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @余胜军
 */
@Component
@Slf4j
@RabbitListener(queues = "integral_queue")
public class IntegralConsumer {
    @Autowired
    private IntegralInfoMapper integralInfoMapper;

    @RabbitHandler
    @Transactional
    public void process(String msg) {
//        try {
            // 转化成积分对象
            IntegralInfoEntity integralInfoEntity =
                    JSONObject.parseObject(msg, IntegralInfoEntity.class);
            QueryWrapper<IntegralInfoEntity> integralInfoEntityQueryWrapper = new QueryWrapper<>();
            integralInfoEntityQueryWrapper.eq("user_id", integralInfoEntity.getUserId());
            integralInfoEntityQueryWrapper.eq("order_id", integralInfoEntity.getOrderId());
            IntegralInfoEntity dbIntegralInfoEntity = integralInfoMapper.selectOne(integralInfoEntityQueryWrapper);
            if (dbIntegralInfoEntity != null) {
                log.info("增加积分失败，之前已经增加过 dbIntegralInfoEntity:{}", dbIntegralInfoEntity);
                return;
            }
            // 向积分表中插入一条积分记录
            int result = integralInfoMapper.insert(integralInfoEntity);
            log.info("增加积分结果:{}", result);
            int i = 1 / 0;
            // 手动提交ack 告诉mq 从队列队列中 删除该消息。
//        } catch (Exception e) {
//            // 手动提交ack 告诉mq 从队列队列中  不要删除该消息 配置重试间隔次数
//            // 如果是消费者代码上bug问题  将该消息放到日志或者是 私信队列中
//            // 将该消息记录下
        // 消费者幂等性 应用层面 全局id 提前查询 如果执行过该流程不会在继续处理 db 全局id唯一约束
//        }
    }
    /***
     * s 默认的情况下 消费者 消费失败 无限次数重试
     * 合理配置重试策略
     *
     */
}
