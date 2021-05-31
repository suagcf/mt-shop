package com.mayikt.api.base;

import com.mayikt.api.constants.Constants;
import com.mayikt.api.utils.MeiteBeanUtils;
import lombok.Data;

/**
 * @version V1.0
 * @description: 微服务接口实现该接口可以使用传递参数可以直接封装统一返回结果集
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 * 私自分享视频和源码属于违法行为。
 */
@Data
public class BaseApiService<T> {

    public BaseResponse<T> setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    /**
     * 返回错误，可以传msg
     *
     * @param msg
     * @return
     */
    public BaseResponse<T> setResultError(String msg) {
        return setResult(Constants.HTTP_RES_CODE_500, msg, null);
    }

    /***
     * 返回成功，可以传data值
     * @param data
     * @return
     */
    public BaseResponse<T> setResultSuccess(T data) {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
    }

    /**
     * 返回成功，沒有data值
     *
     * @return
     */
    public BaseResponse<T> setResultSuccess() {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
    }


    /**
     * 通用封装 通用封装
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */

    public BaseResponse<T> setResult(Integer code, String msg, T data) {
        return new BaseResponse<T>(code, msg, data);
    }


    /**
     * dto 转换do
     *
     * @param dtoEntity
     * @param doClass
     * @param <Do>
     * @return
     */
    public static <Do> Do dtoToDo(Object dtoEntity, Class<Do> doClass) {
        return MeiteBeanUtils.dtoToDo(dtoEntity, doClass);
    }

    /**
     * do转换成dto
     * @param doEntity
     * @param dtoClass
     * @param <Dto>
     * @return
     */
    public static <Dto> Dto doToDto(Object doEntity, Class<Dto> dtoClass) {
        return MeiteBeanUtils.doToDto(doEntity, dtoClass);
    }

    public BaseResponse<T> setResultDb(int dbCount, T successMsg, String errorMsg) {
        return dbCount > 0 ? setResultSuccess(successMsg) :
                setResultError(errorMsg);
    }
}