package com.mayikt.api.impl.entity;

/**
 * UserInfoDo
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("meite_user")
public class UserInfoDo {

    /**
     * userid
     */
    @TableId
    private Long userId;
    /**
     * 手机号码
     */

    private String mobile;

    /**
     * 密码
     */

    private String password;
    /**
     * 用户名称
     */

    private String userName;
    /**
     * 性别 0 男 1女
     */

    private char sex;
    /**
     * 年龄
     */

    private Long age;
    /**
     * 注册时间
     */

    private Date createTime;

    /**
     * 账号是否可以用 1 正常 0冻结
     */

    private char isAvalible;
    /**
     * 用户头像
     */

    private String picImg;
    /**
     * 用户关联 QQ 开放ID
     */

    private String qqOpenId;
    /**
     * 用户关联 微信 开放ID
     */
    private String wxOpenId;
    // 默认的情况下 查询 is_Delete=0
    @TableLogic
    private int isDelete;

    public UserInfoDo(String mobile, String password, String userName) {
        this.mobile = mobile;
        this.password = password;
        this.userName = userName;
    }
}