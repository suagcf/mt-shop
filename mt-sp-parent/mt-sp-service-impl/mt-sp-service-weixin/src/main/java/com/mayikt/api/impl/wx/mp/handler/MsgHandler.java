package com.mayikt.api.impl.wx.mp.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.impl.entity.WechatKeywordDo;
import com.mayikt.api.impl.mapper.KeywordMapper;
import com.mayikt.api.impl.wx.mp.builder.TextBuilder;
import com.mayikt.api.impl.wx.mp.utils.JsonUtils;
import com.mzlion.easyokhttp.HttpClient;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }
        // 1.获取用户在微信公众号中发送的内容
        String key = wxMessage.getContent();
        //2. 根据该key 查询db关键字表中匹配
        QueryWrapper<WechatKeywordDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("keyword_name", key);
        WechatKeywordDo wechatKeywordDo = keywordMapper.selectOne(queryWrapper);
        String resultContent = null;
        if (wechatKeywordDo != null) {
            // 3. 在db中根据表 查询匹配的关键字 直接返回
            resultContent = wechatKeywordDo.getKeywordValue();
        } else {
            // httpclient--okhttp
            resultContent = HttpClient
                    // 请求方式和请求url
                    .get("http://i.itpk.cn/api.php?question=" + key)
                    .asString();
        }
        return new TextBuilder().build(resultContent, wxMessage, weixinService);

    }

}
