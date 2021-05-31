package com.mayikt.api.member;

/* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/
import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.member.dto.req.UserLoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
public interface LoginService {
    /**
     * 提供登录接口
     * @param userLoginDto
     * @return
     */
    @PostMapping("/login")
    BaseResponse<JSONObject> login(@RequestBody UserLoginDto userLoginDto);
}