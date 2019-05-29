package com.grass.api.vo.admin;

import lombok.Data;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:18
 * @Description
 **/
@Data
public class PowerVo {

    private Integer id;

    /**
     * 权限名称
     */
    private String title;

    /**
     * 权限路径
     */
    private String path;

    /**
     * 权限备注
     */
    private String remark;

}
