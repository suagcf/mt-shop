package com.mayikt.api.member.dto.req;

import lombok.Data;

@Data
public class UserLoginDto {
    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码
     */
    private String passWord;
}