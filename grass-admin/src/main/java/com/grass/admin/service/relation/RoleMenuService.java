package com.grass.admin.service.relation;

import com.grass.admin.model.RoleMenu;

import java.util.List;

public interface RoleMenuService {

    List<RoleMenu> findListByRoleId(int roleId);

    List<RoleMenu> findListByRoleIds(List<Integer> roleIds);

    List<Integer> findMenuIdsByRoleId(int roleId);

    List<Integer> findMenuIdsByRoleIds(List<Integer> roleIds);

    Integer add(RoleMenu roleMenu);

    int deleteByRoleId(Integer id);

    int deleteByRoleIds(List<Integer> ids);

    int deleteByMenuId(Integer id);

    int deleteByMenuIds(List<Integer> ids);

    int deleteByRoleAndMenus(Integer roleId,List<Integer> menuIds);

}
