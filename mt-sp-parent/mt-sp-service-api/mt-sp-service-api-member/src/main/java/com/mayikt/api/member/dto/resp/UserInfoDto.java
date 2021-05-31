package com.mayikt.api.member.dto.resp;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoDto {

    /**
     * 手机号码
     */

    private String mobile;


    /**
     * 用户名称
     */

    private String userName;
    /**
     * 性别 0 男 1女
     */

    private char sex;
    /**
     * 年龄
     */

    private Long age;
    /**
     * 注册时间
     */

    private Date createTime;

    /**
     * 账号是否可以用 1 正常 0冻结
     */

    private char isAvalible;
    /**
     * 用户头像
     */

    private String picImg;


}