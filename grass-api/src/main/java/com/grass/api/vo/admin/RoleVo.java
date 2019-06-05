package com.grass.api.vo.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:18
 * @Description
 **/
@Data
public class RoleVo {

    private Integer id;

    /**
     * 角色名称
     */
    @NotBlank(message = "title is null")
    private String title;

    /**
     * 角色备注
     */
    private String remark;

}
