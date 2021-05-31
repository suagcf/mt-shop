package com.mayikt.wx.auth;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import com.mayikt.api.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * 微信公众号授权绑定
 */
@Controller
@RequestMapping("/weChat")
@Slf4j
public class WeChatOAuthController {

    @Value("${mayikt.wx.redirectUri}")
    private String redirectUri;
    @Value("${mayikt.vue.addres}")
    private String vueAddres;
    @Autowired
    private TokenUtils tokenUtils;


    @Autowired
    private WxMpService wxMpService;
    /**
     * 1.需要提供一个微信公众号授权链接绑定地址
     * 2.需要编写一个回调接口地址（获取到授权码）
     * 3.根据该授权码获取用户的openid
     */
    /**
     * 生成授权链接
     * http://127.0.0.1:2000/weChat/authorizedConnection
     *
     * @return
     */
    @RequestMapping("/authorizedConnection")
    public String authorizedConnection() {
        String state = UUID.randomUUID().toString();
        // 使用框架的形式生成授权链接地址
        String returnRedirectUri =
                wxMpService.oauth2buildAuthorizationUrl(redirectUri, WxConsts.OAuth2Scope.SNSAPI_USERINFO, state);
        log.info(">returnRedirectUri{}<", returnRedirectUri);
        // 重定向到 微信服务器实现用户授权
        return "redirect:" + returnRedirectUri;
    }

    /**
     * 微信公众号授权授权成功跳转 前端vue
     *
     * @return
     */
    @RequestMapping("/wechatOAuth")
    public String wechatOAuth(String code, String state) {

        try {
            // 1.根据code码获取用户的openid
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();
            log.info(">用户授权获取到openId{}:<", openId);
            if (StringUtils.isEmpty(openId)) {
                return "error";
            }
            // 2.将真实用户的openid放入到redis中 隐藏接口传递的真实性 将该openid放入到redis中 生成对应的token
            String wxTokenOpenID = tokenUtils.createToken(openId);
            // 3.跳转到vue 重定向到前端vue项目
            return "redirect:" + vueAddres + "?openIdToken=" + wxTokenOpenID;
        } catch (Exception e) {
            log.error("e:{}", e);
            return "error";
        }

    }

}
