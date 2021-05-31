package com.mayikt.api.impl.member;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com
*******************************************************************
//----------------------------------------------------------------*/

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.base.BaseApiService;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.impl.entity.UserInfoDo;
import com.mayikt.api.impl.entity.UserLoginLogDo;
import com.mayikt.api.impl.manage.UserLoginLogManage;
import com.mayikt.api.impl.mapper.UserInfoMapper;
import com.mayikt.api.impl.producer.LoginProducer;
import com.mayikt.api.member.LoginService;
import com.mayikt.api.member.dto.req.UserLoginDto;
import com.mayikt.api.utils.DesensitizationUtil;
import com.mayikt.api.utils.MD5Util;
import com.mayikt.api.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Slf4j
public class LoginServiceImpl extends BaseApiService<JSONObject> implements LoginService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserLoginLogManage userLoginLogManage;
    @Autowired
    private LoginProducer loginProducer;

    /**
     * MD5 需要配置加盐--- 单向加密
     * mayikt ---111111111
     * (pwd+盐值yushengjun644（存放在我们服务器端）)
     * pwd=mayikt+盐值yushengjun644  md5(mayikt+盐值yushengjun644)===222222222222222
     * <p>
     * // 根据手机号码+MD5.MD5(passWord)
     **/
    @Override
    public BaseResponse<JSONObject> login(UserLoginDto userLoginDto) {
        // 验证参数
        String mobile = userLoginDto.getMobile();
        if (StringUtils.isEmpty(userLoginDto.getMobile())) {
            return setResultError("mobile 不能为空!");
        }
        String passWord = userLoginDto.getPassWord();
        if (StringUtils.isEmpty(userLoginDto.getPassWord())) {
            return setResultError("passWord 不能为空!");
        }
        // 从请求头中获取 其他信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String deviceInfor = request.getHeader("deviceInfor");
        String channel = request.getHeader("channel");
        String sourceIp = request.getHeader("sourceIp");
        // 设备信息
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        // 渠道来源
        if (StringUtils.isEmpty(channel)) {
            return setResultError("渠道不能为空");
        }
        // md5加密
        String newPassWord = MD5Util.MD5(passWord);
        QueryWrapper<UserInfoDo> userInfoDoQueryWrapper = new QueryWrapper<>();
        userInfoDoQueryWrapper.eq("MOBILE", mobile);
        userInfoDoQueryWrapper.eq("PASSWORD", newPassWord);
        UserInfoDo userInfoDo = userInfoMapper.selectOne(userInfoDoQueryWrapper);
        if (userInfoDo == null) {
            return setResultError("手机号码或者密码错误");
        }
        // 生成令牌 令牌 做为redis 的key 令牌 value 用户的userid
        Long userId = userInfoDo.getUserId();
        String userToken = tokenUtils.createToken(userId + "");
        JSONObject data = new JSONObject();
        data.put("userToken", userToken);
        log.info(">>userToken:{}<<", userToken);
//        // 异步单独的线程处理
//        userLoginLogManage.asynLoginLog(new UserLoginLogDo(userId, sourceIp, new Date(), userToken,
//                channel, deviceInfor));
        loginProducer.sendMsgLoginFollowUp(userId, sourceIp, new Date(), userToken,
                channel, deviceInfor, userInfoDo.getWxOpenId(), DesensitizationUtil.mobileEncrypt(mobile));
        return setResultSuccess(data);
    }

}
