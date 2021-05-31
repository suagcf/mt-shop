package com.mayikt.api.pay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.base.BaseApiService;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.constants.Constants;
import com.mayikt.api.integral.req.dto.IntegralDto;
import com.mayikt.api.pay.PaymentReconcService;
import com.mayikt.api.pay.config.alipay.AlipayConfig;
import com.mayikt.api.pay.constant.PaymentConstant;
import com.mayikt.api.pay.entity.PaymentInfoEntity;
import com.mayikt.api.pay.feign.IntegralServiceFeign;
import com.mayikt.api.pay.mapper.PaymentInfoMapper;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author 余胜军
 * @ClassName PaymentReconcServiceImpl
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月17日 21:03:00
 */
@RestController
@Slf4j
public class PaymentReconcServiceImpl extends BaseApiService implements PaymentReconcService {
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;
    @Autowired
    private IntegralServiceFeign integralServiceFeign;

    @Override
    @GlobalTransactional
    public BaseResponse<String> payReconciliation(String orderId) {

//        try {
        // 1.验证参数
        if (StringUtils.isEmpty(orderId)) {
            return setResultError("orderId is null");
        }
        // 2.orderId 查询该商户订单信息
        QueryWrapper<PaymentInfoEntity> paymentInfoEntityQueryWrapper = new QueryWrapper<>();
        paymentInfoEntityQueryWrapper.eq("ORDER_ID", orderId);
        PaymentInfoEntity paymentInfoEntity = paymentInfoMapper.selectOne(paymentInfoEntityQueryWrapper);
        if (paymentInfoEntity == null) {
            return null;
        }
        // 3.主动调用支付宝的接口 查询支付的状态 支付成功 支付的金额
        BigDecimal totalAmount = outTradeNoReconciliation(orderId);
        if (totalAmount == null) {
            return setResultError("该交易不存在!");
        }
        // 4.用户在支付的金额与下单金额比对 如果一致的话
        if (!(paymentInfoEntity.getPayAmount().compareTo(totalAmount) == 0)) {
            return setResultError("用户支付的金额与下单金额不一致！");
        }
        // 5.更新支付表中的订单状态为已经支付成功
        if (PaymentConstant.PAYMENT_STATUS_FAIL.equals(paymentInfoEntity.getPaymentStatus())) {
            // 修改支付状态为已经支付
            paymentInfoEntity.setPaymentStatus(PaymentConstant.PAYMENT_STATUS_SUCCESS);
            paymentInfoMapper.updateById(paymentInfoEntity);
        }
        // 6.调用积分服务接口增加积分 rpc
        IntegralDto integralDto = new IntegralDto(3000l, paymentInfoEntity.getUserId(), paymentInfoEntity.getOrderId());
        BaseResponse<String> stringBaseResponse = integralServiceFeign.addIntegral(integralDto);
        if (!Constants.HTTP_RES_CODE_200.equals(stringBaseResponse.getCode())) {
            return setResultSuccess("调用积分服务接口失败,或者已经同步完成");
        }
        // 支付服务宕机了 支付挂了 支付报错
//        try {
//            Thread.sleep(100000);
//        } catch (Exception e) {
//
//        }
        int j = 1 / 0;

        return setResultError("同步状态成功");
//        } catch (Exception e) {
////            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            log.error("e:{}", e);
//            return setResultError("系统错误");
//        }

    }

    private BigDecimal outTradeNoReconciliation(String orderId) {

        try {

            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
            //设置请求参数
            AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
            //请二选一设置
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderId + "\"}");
            //请求
            String result = alipayClient.execute(alipayRequest).getBody();
            log.error(">result:{}<", result);
            JSONObject dataJSONObject = JSONObject.parseObject(result);
            JSONObject response = dataJSONObject.getJSONObject("alipay_trade_query_response");
            Integer code = response.getInteger("code");
            if (!PaymentConstant.RPC_PAY_SUCCESS.equals(code)) {
                return null;
            }
            //total_amount
            BigDecimal totalAmount = response.getBigDecimal("total_amount");
            return totalAmount;
        } catch (Exception e) {
            return null;
        }
    }
}
