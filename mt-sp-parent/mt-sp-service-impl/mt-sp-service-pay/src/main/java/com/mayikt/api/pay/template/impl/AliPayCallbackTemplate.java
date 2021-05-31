package com.mayikt.api.pay.template.impl;

import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.pay.config.alipay.AlipayConfig;
import com.mayikt.api.pay.constant.PaymentConstant;
import com.mayikt.api.pay.entity.PaymentInfoEntity;
import com.mayikt.api.pay.holder.PayThreadInfoHolder;
import com.mayikt.api.pay.mapper.PaymentInfoMapper;
import com.mayikt.api.pay.template.AbstractPayCallbackTemplate;
import com.mayikt.api.pay.template.AsynCallbackEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@Slf4j
public class AliPayCallbackTemplate extends AbstractPayCallbackTemplate {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Override
    protected boolean verifySignature() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //获取支付宝GET过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            params.remove("channelId");
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
            //——请在这里编写您的程序（以下代码仅作参考）——
            if (!signVerified) {
                return false;
            }
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            PayThreadInfoHolder.add(params);
            return true;
        } catch (Exception e) {
            log.error(">>e:{}<<", e);
            return false;
        }

    }

    @Override
    protected AsynCallbackEntity asyncService() {
        try {
            // 修改订单状态为已经成功
            Map<String, String> params = PayThreadInfoHolder.get();
            // 用户下单生成订单号码
            String outTradeNo = params.get("out_trade_no");
            /**
             *  根据订单号码查询：outTradeNo
             *
             */
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("ORDER_ID", outTradeNo);
            PaymentInfoEntity paymentInfoEntity = paymentInfoMapper.selectOne(queryWrapper);
            if (paymentInfoEntity == null) {
                // 先记录下 该笔订单异常 后期检查
                return setResult(true);
            }
            // 该笔订单是为已经支付状态 幂等防止重复 重试机制 间隔 重试两个请求
            if (PaymentConstant.PAYMENT_STATUS_SUCCESS.equals(paymentInfoEntity.getPaymentStatus())) {
                return setResult(true);
            }

            //用户下单支付金额
            String totalAmount = params.get("total_amount");
            if (!paymentInfoEntity.getPayAmount().equals(new BigDecimal(totalAmount))) {
                // 说明用户支付金额与下单金额不一致 该笔订单应该设为异常
                return setResult(true);
            }
            // 修改支付状态为已经支付
            paymentInfoEntity.setPaymentStatus(PaymentConstant.PAYMENT_STATUS_SUCCESS);
            paymentInfoMapper.updateById(paymentInfoEntity);
            return setResult(true, paymentInfoEntity);
        } catch (Exception e) {
            log.error(">e:{}<", e);
            return setResult(false);
        }

    }

    @Override
    protected String resultSuccess() {
        return "success";
    }

    @Override
    protected String resultFail() {
        return "fail";
    }
}
