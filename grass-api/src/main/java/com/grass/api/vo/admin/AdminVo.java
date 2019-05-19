package com.grass.api.vo.admin;

import lombok.Data;

import java.util.Date;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:05
 * @Description
 **/
@Data
public class AdminVo {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String icon;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别1男2女
     */
    private Integer sex;

    private String address;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态0不可用1正常
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

}
