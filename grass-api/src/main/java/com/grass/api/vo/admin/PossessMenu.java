package com.grass.api.vo.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Fenglixiong
 * @Date: 2019/6/6 16:38
 * @Description: 某个角色拥有的菜单和所有菜单
 */
@Data
@NoArgsConstructor
public class PossessMenu {

    private List<MenuVo> allMenus;

    private List<MenuVo> hasMenus;

    public PossessMenu(List<MenuVo> allMenus, List<MenuVo> hasMenus) {
        this.allMenus = allMenus;
        this.hasMenus = hasMenus;
    }

}
