package com.mayikt.api.impl.manage;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.impl.entity.UserLoginLogDo;
import com.mayikt.api.impl.mapper.UserLoginLogMapper;
import com.mayikt.api.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserLoginLogManage {
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Async("taskExecutor")
    public void asynLoginLog(UserLoginLogDo userLoginLogDo) {
        // 1.唯一登录处理  根据userid+登录来源（PC、安卓）
        uniqueLogin(userLoginLogDo);
        // 2.插入登录日志信息
        insertUserLoginLogDo(userLoginLogDo);
        /**
         * 3.记录登录日志信息 1s
         * 4.调用短信、邮件、微信模板接口 提示登录信息 2s
         * 5.唯一登录处理 2s
         * 6.赠送优惠券操作
         * 7.其他操作
         */

    }

    private void uniqueLogin(UserLoginLogDo userLoginLogDo) {
        log.info("uniqueLogin 唯一登录处理" + userLoginLogDo);
        QueryWrapper<UserLoginLogDo> userLoginLogDoQueryWrapper = new QueryWrapper<>();
        userLoginLogDoQueryWrapper.eq("user_id", userLoginLogDo.getUserId());
        userLoginLogDoQueryWrapper.eq("channel", userLoginLogDo.getChannel());
        UserLoginLogDo dbUserLoginLogDo = userLoginLogMapper.selectOne(userLoginLogDoQueryWrapper);
        // 说明之前从来没有登录过
        if (dbUserLoginLogDo == null) {
            return;
        }
        // 逻辑删除 将之前登录记录 token状态改为逻辑删除 1
        int result = userLoginLogMapper.deleteById(dbUserLoginLogDo.getId());
        if (result > 0) {
            redisUtil.delKey(dbUserLoginLogDo.getLoginToken());
        }

    }

    private void insertUserLoginLogDo(UserLoginLogDo userLoginLogDo) {
        int result = userLoginLogMapper.insert(userLoginLogDo);
        log.info("result:" + result);
    }

}
