package com.mayikt.api.impl.member;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.base.BaseApiService;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.impl.entity.UserInfoDo;
import com.mayikt.api.impl.mapper.UserInfoMapper;
import com.mayikt.api.member.WechatBindingService;
import com.mayikt.api.member.dto.req.UserLoginWxOpenIdReqDto;
import com.mayikt.api.utils.MD5Util;
import com.mayikt.api.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WechatBindingServiceImpl extends BaseApiService<String> implements WechatBindingService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public BaseResponse<String> wechatBindUserOpenId(UserLoginWxOpenIdReqDto userLoginWxOpenIdReqDto) {
        // 验证参数
        String mobile = userLoginWxOpenIdReqDto.getMobile();
        if (StringUtils.isEmpty(userLoginWxOpenIdReqDto.getMobile())) {
            return setResultError("mobile 不能为空!");
        }
        String passWord = userLoginWxOpenIdReqDto.getPassWord();
        if (StringUtils.isEmpty(userLoginWxOpenIdReqDto.getPassWord())) {
            return setResultError("passWord 不能为空!");
        }
        String newPassWord = MD5Util.MD5(passWord);
        QueryWrapper<UserInfoDo> userInfoDoQueryWrapper = new QueryWrapper<>();
        userInfoDoQueryWrapper.eq("MOBILE", mobile);
        userInfoDoQueryWrapper.eq("PASSWORD", newPassWord);
        UserInfoDo userInfoDo = userInfoMapper.selectOne(userInfoDoQueryWrapper);
        if (userInfoDo == null) {
            return setResultError("手机号码或者密码错误");
        }
        //根据wxOpenIdToken 获取用户真实的openid
        String wxOpenIdToken = userLoginWxOpenIdReqDto.getWxOpenIdToken();
        if (StringUtils.isEmpty(wxOpenIdToken)) {
            return setResultError("wxOpenIdToken is null");
        }
        String openid = tokenUtils.getTokenValue(wxOpenIdToken);
        if (StringUtils.isEmpty(openid)) {
            return setResultError("tokenValue is null");
        }
        // 更新到db openid
        userInfoDo.setWxOpenId(openid);
        int result = userInfoMapper.updateById(userInfoDo);
        if (result <= 0) {
            return setResultError("关联openid失败");
        }
        return setResultSuccess("关联成功!");
    }
}
