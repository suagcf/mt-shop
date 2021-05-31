package com.mayikt.api.impl.member;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com
*******************************************************************
//----------------------------------------------------------------*/


import com.alibaba.fastjson.JSON;
import com.mayikt.api.base.BaseApiService;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.impl.entity.UserDo;
import com.mayikt.api.impl.feign.WeChatServiceFeign;

import com.mayikt.api.impl.mapper.UserMapper;
import com.mayikt.api.member.MemberService;
import com.mayikt.api.member.dto.req.UserReqDto;
import com.mayikt.api.member.dto.resp.UserRespDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@CrossOrigin
public class MemberServiceImpl extends BaseApiService implements MemberService {
    @Autowired
    private WeChatServiceFeign weChatServiceFeign;
    @Autowired
    private UserMapper userMapper;
    @Value("${mayikt.userName}")
    private String userName;

    @Override
    public String memberToWeiXin(Integer a) {
        // Feign调用微信服务接口
        return weChatServiceFeign.getWeChat(a);
    }

//    @Override
//    public Object updateUser(Map<String, String> map) {
//        //1.接受参数
//        String jsonStr = JSON.toJSONString(map);
//        UserEntity user = JSON.parseObject(jsonStr, UserEntity.class);
//        Integer userId = user.getUserId();
//        //2.update
//        int i = userMapper.updateById(user);
//        if (i <= 0) {
//            return "error";
//        }
//        // 3.查询db数据
//        UserEntity userEntity = userMapper.selectById(userId);
//        return userEntity == null ? "error" : userEntity;
//    }

    @Override
    public BaseResponse<UserRespDto> updateUserDto(UserReqDto userReqDto) {
        // 1.验证参数----
        // 2.userReqDto
        // 3.userReqDto属性值赋值 userDo
        UserDo updateUserDo = doToDto(userReqDto, UserDo.class);
        // 4.updateById
        int i = userMapper.updateById(updateUserDo);
        if (i <= 0) {
            return setResultError("修改失败!");
        }
        // 5.查询db数据
        Integer userId = updateUserDo.getUserId();
        UserDo getDbUserDo = userMapper.selectById(userId);
        if (getDbUserDo == null) {
            return setResultError("查询数据为空.");
        }
        // UserDo 转化成dto
        UserRespDto userRespDto = doToDto(getDbUserDo, UserRespDto.class);
        return setResultSuccess(userRespDto);
    }

    @Override
    public BaseResponse<String> getTestConfig() {
        // Access-Control-Allow-Origin ,*
        //@CrossOrigin aOP的方式实现拦截所有请求 给每个请求上加上Access-Control-Allow-Origin ,*
        return setResultSuccess(userName);
    }


}
