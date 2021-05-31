package com.mayikt.api.member;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com
*******************************************************************
//----------------------------------------------------------------*/


import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.member.dto.req.UserReqDto;
import com.mayikt.api.member.dto.resp.UserRespDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public interface MemberService {
    /**
     * 会员调用到微信服务接口
     *
     * @param a
     * @return
     */
    @GetMapping("memberToWeiXin")
    String memberToWeiXin(Integer a);

//    /**
//     * updateUser
//     *
//     * @param map
//     * @return
//     */
//    @PostMapping("updateUser")
//    Object updateUser(@RequestBody Map<String, String> map);

    /*
     updateUserDto
     */
    @PostMapping("updateUserDto")
    BaseResponse<UserRespDto> updateUserDto(@RequestBody UserReqDto userReqDto);

    @RequestMapping("/getTestConfig")
    BaseResponse<String> getTestConfig();
}
