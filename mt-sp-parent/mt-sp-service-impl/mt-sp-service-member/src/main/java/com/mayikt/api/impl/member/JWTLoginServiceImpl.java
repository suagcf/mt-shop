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
import com.mayikt.api.impl.mapper.UserInfoMapper;
import com.mayikt.api.impl.utils.MayiktJwtUtils;
import com.mayikt.api.member.JWTLoginService;
import com.mayikt.api.member.dto.req.UserLoginDto;
import com.mayikt.api.utils.MD5Util;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTLoginServiceImpl extends BaseApiService<JSONObject> implements JWTLoginService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public BaseResponse<JSONObject> loginJwt(UserLoginDto userLoginDto) {
        // 验证参数
        String mobile = userLoginDto.getMobile();
        if (StringUtils.isEmpty(userLoginDto.getMobile())) {
            return setResultError("mobile 不能为空!");
        }
        String passWord = userLoginDto.getPassWord();
        if (StringUtils.isEmpty(userLoginDto.getPassWord())) {
            return setResultError("passWord 不能为空!");
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
        // 生成jwttoken
        String jwt = MayiktJwtUtils.generateJsonWebToken(userInfoDo);
        JSONObject data = new JSONObject();
        data.put("jwt", jwt);
        return setResultSuccess(data);
    }

    @Override
    public BaseResponse<JSONObject> jwtVerification(String jwt) {
        if (StringUtils.isEmpty(jwt)) {
            return setResultError("jwt is null");
        }
        Claims claims = MayiktJwtUtils.checkJWT(jwt);
        if(claims==null){
            return setResultError("jwt error");
        }
        // 操作------jwt 后台管理系统平台 互联网
        return setResultSuccess();
    }

}
