package com.mayikt.pay.consumer.service;

import com.mayikt.pay.consumer.constant.PaymentConstant;
import com.mayikt.pay.consumer.entity.PaymentInfoEntity;
import com.mayikt.pay.consumer.mapper.PaymentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 余胜军
 * @ClassName PayOrderTimeoutService
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月22日 21:13:00
 */
@Service
public class PayOrderTimeoutService {
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    public boolean orderTimeout(Long payId) {
        // 消费者获取 支付的id  消费者如何消费 批量获取msg、多个消费者 、查询db   查询n个未支付状态id
        // 1.根据该支付id 查询支付订单信息 状态   异步回调中如果用户支付成功了，从rabbitmq 将该消息删除 清理过期key
        PaymentInfoEntity paymentInfoEntity = paymentInfoMapper.selectById(payId);
        if (paymentInfoEntity == null) {
            return false;
        }

        // 2.如果支付状态是为未支付的话，则将该状态该已超时
        if (!PaymentConstant.PAYMENT_STATUS_NOT.equals(paymentInfoEntity.getPaymentStatus())) {
            return false;
        }
        // 3. 主动调用下  支付宝接口 根据 订单状态查询 支付宝这边是否已经支付了---31 32分钟 调用支付宝接口查询
        // 用户跳转到支付页面支付超时30分钟
        // 3.调用库存接口 增加库存（33）  修改该状态为超时
        paymentInfoEntity.setPaymentStatus(PaymentConstant.PAYMENT_STATUS_TIMEOUT);
        paymentInfoMapper.updateById(paymentInfoEntity);
        // 调用库存接口 新增库存  
        return true;
    }
}
