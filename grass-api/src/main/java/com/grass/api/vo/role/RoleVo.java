package com.grass.api.vo.role;

import lombok.Data;

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
    private String title;

    /**
     * 角色备注
     */
    private String remark;

}
