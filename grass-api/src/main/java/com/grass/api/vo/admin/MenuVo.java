package com.grass.api.vo.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/29 22:42
 * @Description
 **/
@Data
@NoArgsConstructor
public class MenuVo {

    private Integer id;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 父级菜单
     */
    private Integer parentId;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单图标代码
     */
    private String icon;

    /**
     * 菜单排序
     */
    private Integer order;

    /**
     * 备注
     */
    private String remark;

    /**
     * 子集菜单
     */
    private List<MenuVo> children;

}
