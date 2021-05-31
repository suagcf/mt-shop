package com.mayikt.api.base;

import lombok.Data;

/**
 * 
 * 
 * @description: 微服务接口统一返回码
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Data
public class BaseResponse<T> {

   /**
    * 返回码
    */
   private Integer code;
   /**
    * 消息
    */
   private String msg;
   /**
    * 返回
    */
   private T data;
   // 分页

   public BaseResponse() {

   }

   public BaseResponse(Integer code, String msg, T data) {
      super();
      this.code = code;
      this.msg = msg;
      this.data = data;
   }

}