package com.grass.api.vo.admin;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:05
 * @Description
 **/
@Data
@NoArgsConstructor
public class AdminVo {

    private Long id;

    /**
     * 用户名
     */
    @NotNull(message = "username not be null")
    private String username;

    /**
     * 昵称
     */
    @NotNull(message = "nickname not be null")
    private String nickname;

    /**
     * 用户头像
     */
    private String icon;

    /**
     * 电话
     */
    @NotNull(message = "phone not be null")
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别1男2女
     */
    @Range(min = 1,max = 2)
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
    @Range(min = 0,max = 1)
    private Integer status;

    private Date createTime;

    private Date updateTime;

    public AdminVo(Long id,String username) {
        this.id = id;
        this.username = username;
    }
}
