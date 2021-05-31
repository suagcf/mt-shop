package com.mayikt.api.impl.entity;

/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mayikt_user")
public class UserDo {
    @TableId
    private Integer userId;
    private String userName;
    private String userPwd;
    private Integer userAge;
    private String userAddres;
    private Integer userIntegral;
}