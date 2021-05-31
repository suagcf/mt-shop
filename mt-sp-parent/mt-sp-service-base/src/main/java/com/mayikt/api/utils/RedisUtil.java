package com.mayikt.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
   @Autowired
   private StringRedisTemplate stringRedisTemplate;

   /**
    * 存放string类型
    * 
    * @param key
    *            key
    * @param data
    *            数据
    * @param timeout
    *            超时间
    */
   public void setString(String key, String data, Long timeout) {
      stringRedisTemplate.opsForValue().set(key, data);
      if (timeout != null) {
         stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
      }
   }

   /**
    * 存放string类型
    * 
    * @param key
    *            key
    * @param data
    *            数据
    */
   public void setString(String key, String data) {
      setString(key, data, null);
   }

   /**
    * 根据key查询string类型
    * 
    * @param key
    * @return
    */
   public String getString(String key) {
      String value = stringRedisTemplate.opsForValue().get(key);
      return value;
   }

   /**
    * 根据对应的key删除key
    * 
    * @param key
    */
   public void delKey(String key) {
      stringRedisTemplate.delete(key);
   }



   public void setList(String key, List<String> listToken) {
      stringRedisTemplate.opsForList().leftPushAll(key, listToken);
   }

   public StringRedisTemplate getStringRedisTemplate() {
      return stringRedisTemplate;
   }
}