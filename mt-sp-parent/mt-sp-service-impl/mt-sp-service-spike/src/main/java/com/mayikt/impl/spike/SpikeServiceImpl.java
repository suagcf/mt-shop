package com.mayikt.impl.spike;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.util.concurrent.RateLimiter;
import com.mayikt.api.base.BaseApiService;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.spike.SpikeService;
import com.mayikt.api.utils.TokenUtils;
import com.mayikt.impl.spike.entity.CommodityOrderEntity;
import com.mayikt.impl.spike.entity.CommoditySpecsEntity;
import com.mayikt.impl.spike.manager.SpikeManager;
import com.mayikt.impl.spike.mapper.CommodityOrderMapprer;
import com.mayikt.impl.spike.mapper.CommoditySpecsMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author 余胜军
 * @ClassName SpikeServiceImpl
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年05月06日 16:03:00
 */
@RestController
public class SpikeServiceImpl extends BaseApiService<String> implements SpikeService {
    // 指定每秒放1个令牌
    private RateLimiter limiter = RateLimiter.create(1);
    @Autowired
    private CommoditySpecsMapper commoditySpecsMapper;
    @Autowired
    private CommodityOrderMapprer commodityOrderMapprer;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private SpikeManager spikeManager;


    @Override
    public BaseResponse<String> spike(Long userId, Long commoditySpecsId) {
        // 1.验证参数
        if (userId == null) {
            return setResultError("userId is null");
        }
        if (commoditySpecsId == null) {
            return setResultError("seckillId is null");
        }
//        //2.api接口限流
//        try {
//            boolean result = limiter.tryAcquire(1, TimeUnit.SECONDS);
//            if (!result) {
//                return setResultError("当前访问人数过多，请稍后继续重试!");
//            }
//        } catch (Exception e) {
//
//        }
        // 3.从Redis中获取令牌，
        String spikeToken = tokenUtils.getListKeyToken(commoditySpecsId + "");
        if (StringUtils.isEmpty(spikeToken)) {
            return setResultError("很抱歉，当前商品已经售空，请下次再来");
        }
        // 分布式锁
//        // 3.开始实现扣库存
//        CommoditySpecsEntity commoditySpecsEntity = commoditySpecsMapper.selectById(commoditySpecsId);
//        if (commoditySpecsEntity == null) {
//            return setResultError("未查找到该商品内容");
//        }
//        Long commodityStok = commoditySpecsEntity.getCommodityStok();
//        if (commodityStok <= 0) {
//            return setResultError("未查找到该商品内容");
//        }
//        int result = commoditySpecsMapper.deductionInventory(commoditySpecsId);
//        if (result <= 0) {
//            return setResultError("秒杀失败，库存已经没有啦!");
//        }
//        4. 插入订单
//        CommodityOrderEntity commodityOrderEntity = new CommodityOrderEntity(commoditySpecsId, userId, new BigDecimal(99), 0);
//        commodityOrderMapprer.insert(commodityOrderEntity);
//        spikeManager.sendSpikeMsg(userId, commoditySpecsId, spikeToken);
        return setResultSuccess("正在秒杀抢购中");
    }
}
