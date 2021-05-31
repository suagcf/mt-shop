package com.mayikt.api.weixin.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LoginReminderReqDto {
    private String openId;
    private String phone;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    private String loginIp;
    private String equipment;

    public LoginReminderReqDto(String phone, Date loginTime, String loginIp, String equipment, String openId) {
        this.phone = phone;
        this.loginTime = loginTime;
        this.loginIp = loginIp;
        this.equipment = equipment;
        this.openId = openId;
    }

}