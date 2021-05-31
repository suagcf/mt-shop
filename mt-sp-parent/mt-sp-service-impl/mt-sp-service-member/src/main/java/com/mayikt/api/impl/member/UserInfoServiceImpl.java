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
import com.mayikt.api.member.UserInfoService;
import com.mayikt.api.member.dto.resp.UserInfoDto;
import com.mayikt.api.utils.DesensitizationUtil;
import com.mayikt.api.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class UserInfoServiceImpl extends BaseApiService<UserInfoDto> implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public BaseResponse<UserInfoDto> getUserInfo(String token) {
        if (StringUtils.isEmpty(token)) {
            return setResultError("token 不能为空!");
        }
        // 2.根据token查询 Redis 获取用户的userid
        String tokenValue = tokenUtils.getTokenValue(token);
        if (StringUtils.isEmpty(tokenValue)) {
            return setResultError("token 可能失效!");
        }
        Long userId = Long.parseLong(tokenValue);
        // 3.根据用户的userid 查询用户信息
        UserInfoDo userInfoDo = userInfoMapper.selectById(userId);
        if (userInfoDo == null) {
            return setResultError("token错误！");
        }
        UserInfoDto userInfoDto = doToDto(userInfoDo, UserInfoDto.class);
        userInfoDto.setMobile(DesensitizationUtil.mobileEncrypt(userInfoDto.getMobile()));
        return setResultSuccess(userInfoDto);
    }

    @Override
    public BaseResponse<UserInfoDto> testSubtableUser(Long userId) {
        UserInfoDo userInfoDo = userInfoMapper.selectById(userId);
        UserInfoDto userInfoDto = doToDto(userInfoDo, UserInfoDto.class);
        return setResultSuccess(userInfoDto);
    }

    @Override
    public List<Object> selectByUserInfo() {
        QueryWrapper<UserInfoDo> userInfoDoQueryWrapper = new QueryWrapper<>();
//        userInfoDoQueryWrapper.eq("")
        List<UserInfoDo> userInfoDos = userInfoMapper.selectList(userInfoDoQueryWrapper);
        return Collections.singletonList(userInfoDos);
    }

    @Override
    public String insertUser() {
        for (int i = 0; i < 10; i++) {
            UserInfoDo mayikt = new UserInfoDo("1111111111" + i, "112", "mayikt");
            userInfoMapper.insert(mayikt);
        }
        return "ok";
    }

    @Override
    public String memberUser(int age) {
        int j = 1 / age;
        return j + "";
    }
}
