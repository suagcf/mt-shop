package com.mayikt.api.weixin;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.mayikt.api.base.BaseResponse;
import com.mayikt.api.weixin.dto.req.LoginReminderReqDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface WechatTemplateService {
    /**
     * 根据用户的openId群发消息
     * @return
     */
    @PostMapping("sendTemplate")
    BaseResponse<String> sendTemplate(@RequestBody LoginReminderReqDto LoginReminderReqDto);

}
