package com.mayikt.api.integral.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.base.BaseApiService;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.integral.IntegralService;
import com.mayikt.api.integral.impl.entity.IntegralInfoEntity;
import com.mayikt.api.integral.impl.mapper.IntegralInfoMapper;
import com.mayikt.api.integral.req.dto.IntegralDto;
//import io.seata.spring.annotation.GlobalTransactional;
//import io.seata.spring.annotation.GlobalTransactional;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余胜军
 * @ClassName IntegralServiceImpl
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月17日 17:06:00
 */
@RestController
@Slf4j
public class IntegralServiceImpl extends BaseApiService<String> implements IntegralService {
    @Autowired
    private IntegralInfoMapper integralInfoMapper;

    @Override
    @GlobalTransactional
    public BaseResponse<String> addIntegral(IntegralDto integralDto) {
        // 1.验证参数
        // 2.查询db
        QueryWrapper<IntegralInfoEntity> integralInfoEntityQueryWrapper = new QueryWrapper<>();
        integralInfoEntityQueryWrapper.eq("user_id", integralDto.getUserId());
        integralInfoEntityQueryWrapper.eq("order_id", integralDto.getOrderId());
        IntegralInfoEntity dbIntegralInfoEntity = integralInfoMapper.selectOne(integralInfoEntityQueryWrapper);
        if (dbIntegralInfoEntity != null) {
            log.info("增加积分失败，之前已经增加过 dbIntegralInfoEntity:{}", dbIntegralInfoEntity);
            return setResultError("积分已经存在不能继续重复增加");
        }
        // 积分表中插入一条积分记录
        IntegralInfoEntity integralInfoEntity = dtoToDo(integralDto, IntegralInfoEntity.class);
        integralInfoEntity.setDeleted(0);
        int result = integralInfoMapper.insert(integralInfoEntity);
        log.info("增加积分结果:{}", result);
        return setResultSuccess();
    }
}