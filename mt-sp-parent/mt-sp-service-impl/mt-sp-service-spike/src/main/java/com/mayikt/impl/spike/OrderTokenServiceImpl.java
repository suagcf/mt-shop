package com.mayikt.impl.spike;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.base.BaseApiService;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.spike.OrderTokenService;
import com.mayikt.api.utils.TokenUtils;
import com.mayikt.impl.spike.entity.CommoditySpecsEntity;
import com.mayikt.impl.spike.mapper.CommoditySpecsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余胜军
 * @ClassName OrderTokenServiceImpl
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年05月06日 19:05:00
 */
@RestController
public class OrderTokenServiceImpl extends BaseApiService implements OrderTokenService {
    @Autowired
    private CommoditySpecsMapper commoditySpecsMapper;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public BaseResponse<JSONObject> addSpikeToken(Long seckillId) {
        // 1.验证参数
        if (seckillId == null) {
            return setResultError("商品库存id不能为空!");
        }
        CommoditySpecsEntity commoditySpecsEntity = commoditySpecsMapper.selectById(seckillId);
        if (commoditySpecsEntity == null) {
            return setResultError("未查找到该商品内容");
        }
        Long commodityStok = commoditySpecsEntity.getCommodityStok();
        if (commodityStok <= 0) {
            return setResultError("库存已经小于=0");
        }
        // 2.使用多线程异步生产令牌
        createSeckillToken(seckillId, commoditySpecsEntity.getCommodityStok());

        return setResultSuccess("token生成成功");
    }

    private void createSeckillToken(Long seckillId, Long tokenQuantity) {
        tokenUtils.createListToken("seckill_", seckillId + "", tokenQuantity);
    }

}
