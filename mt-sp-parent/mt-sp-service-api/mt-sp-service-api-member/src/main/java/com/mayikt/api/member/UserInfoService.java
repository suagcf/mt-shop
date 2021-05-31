package com.mayikt.api.member;

import com.mayikt.api.base.BaseApiService;
import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.member.dto.resp.UserInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserInfoService {

    /**
     * 根据token获取用户的信息
     *
     * @param token
     * @return
     */
    @GetMapping("getUserInfo")
    BaseResponse<UserInfoDto> getUserInfo(@RequestParam("token") String token);

    /**
     * 测试接口
     *
     * @param userId
     * @return
     */
    @RequestMapping("/testSubtableUser")
    BaseResponse<UserInfoDto> testSubtableUser(Long userId);

    /**
     * 测试接口
     *
     * @param
     * @return
     */
    @RequestMapping("/testSelectByUserInfo")
    List<Object> selectByUserInfo();

    /**
     * 测试接口
     *
     * @param
     * @return
     */
    @RequestMapping("/insertUser")
    String insertUser();

    /**
     * memberUser
     *
     * @return
     */
    @RequestMapping("/memberUser")
    String memberUser(int age);
}