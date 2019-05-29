package com.grass.admin.service;

import com.grass.api.vo.admin.MenuVo;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/29 22:46
 * @Description
 **/
public interface MenuService {

    List<MenuVo> findList();

    List<MenuVo> findListByAdminId(Long adminId);

    List<MenuVo> findListByRoleId(Integer roleId);

    List<MenuVo> findListByRoleIds(List<Integer> roleIds);

    List<MenuVo> findListByMenuIds(List<Integer> menuIds);

}
