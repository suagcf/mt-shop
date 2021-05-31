package com.mayikt.api.pay.template;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.pay.entity.PaymentInfoEntity;
import com.mayikt.api.pay.mq.ProducerIntegral;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @作者：余胜军
 * @QQ644064779
 */
@Slf4j
public abstract class AbstractPayCallbackTemplate {
    @Autowired
    private ProducerIntegral producerIntegral;

    /**
     * 抽象方法定义共同行为的骨架
     *
     * @return
     */
    public String asynCallback() {
        // 1.记录log日志(相同的)
        addLog();
        // 2.验证签名（验证参数是否正确） (不同的)
        boolean resultVerifySignature = verifySignature();
        if (!resultVerifySignature) {
            return resultFail();
        }
        // 3.处理该参数的业务逻辑 (不同的)
        AsynCallbackEntity asynCallbackEntity = asyncService();
        if (!asynCallbackEntity.getResult()) {
            return resultFail();
        }
        PaymentInfoEntity paymentInfoEntity = asynCallbackEntity.getPaymentInfoEntity();
        if (paymentInfoEntity == null) {
            return resultSuccess();
        }
        // 4.调用积分服务接口 （基于rabbitmq环境构建）  (相同的)  feign
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", paymentInfoEntity.getUserId());
        jsonObject.put("orderId", paymentInfoEntity.getOrderId());
        jsonObject.put("value", 3000);
        producerIntegral.sendMsg(jsonObject.toJSONString());
        // 重试过程中userid+orderId肯定是相同的  如何幂等问题全局id 业务
        /**
         *  支付服务更新支付状态成功 投递积分消息失败 定时任务 查询已经支付成功订单 对账如果s
         */
        return resultSuccess();
    }

    ;

    public void addLog() {
        log.info(">>1.记录log日志(相同的)<<");
    }

    /**
     * 验证签名（验证参数是否正确） (不同的)
     *
     * @return
     */
    protected abstract boolean verifySignature();

    /**
     * 处理该参数的业务逻辑 (不同的)
     *
     * @return
     */
    protected abstract AsynCallbackEntity asyncService();

    /**
     * 返回成功
     *
     * @return
     */
    protected abstract String resultSuccess();

    /**
     * 返回失败
     *
     * @return
     */
    protected abstract String resultFail();

    public void rpcIntegral() {

    }

    public AsynCallbackEntity setResult(boolean result, PaymentInfoEntity paymentInfoEntity) {
        return new AsynCallbackEntity(true, paymentInfoEntity);
    }

    public AsynCallbackEntity setResult(boolean result) {
        return new AsynCallbackEntity(true, null);
    }
}
