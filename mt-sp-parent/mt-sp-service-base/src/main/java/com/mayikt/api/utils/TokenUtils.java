package com.mayikt.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TokenUtils {
    @Autowired
    private RedisUtil redisUtil;

    public String createToken(String value) {
        String token = UUID.randomUUID().toString().replace("-", "");
        redisUtil.setString(token, value);
        return token;
    }

    public String getTokenValue(String token) {
        return redisUtil.getString(token);
    }


    public void createListToken(String keyPrefix, String redisKey, Long tokenQuantity) {
        List<String> listToken = getListToken(keyPrefix, tokenQuantity);
        redisUtil.setList(redisKey, listToken);
    }

    public List<String> getListToken(String keyPrefix, Long tokenQuantity) {
        List<String> listToken = new ArrayList<>();
        for (int i = 0; i < tokenQuantity; i++) {
            String token = keyPrefix + UUID.randomUUID().toString().replace("-", "");
            listToken.add(token);
        }
        return listToken;

    }

    public String getListKeyToken(String key) {
        String value = redisUtil.getStringRedisTemplate().opsForList().leftPop(key);
        return value;
    }
}