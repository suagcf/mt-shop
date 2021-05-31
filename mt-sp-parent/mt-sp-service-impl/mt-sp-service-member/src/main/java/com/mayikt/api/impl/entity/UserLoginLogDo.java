package com.mayikt.api.impl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_login_log")
public class UserLoginLogDo {
    @TableId
    private Long id;
    private Long userId;

    private String loginIp;
    private Date loginTime;
    private String loginToken;
    private String channel;
    private String equipment;
    public UserLoginLogDo(){

    }
    public UserLoginLogDo(Long userId, String loginIp, Date loginTime, String loginToken, String channel, String equipment) {
        this.userId = userId;
        this.loginIp = loginIp;
        this.loginTime = loginTime;
        this.loginToken = loginToken;
        this.channel = channel;
        this.equipment = equipment;
    }

    // 默认的情况下 查询 is_Delete=0
    @TableLogic
    private int isDelete;
}