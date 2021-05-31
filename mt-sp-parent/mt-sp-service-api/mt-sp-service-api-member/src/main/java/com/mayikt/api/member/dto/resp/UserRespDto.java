package com.mayikt.api.member.dto.resp;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import lombok.Data;

@Data
public class UserRespDto {
    private Integer userId;
    private String userName;
    private Integer userAge;
    private String userAddres;
    private Integer userIntegral;
}
