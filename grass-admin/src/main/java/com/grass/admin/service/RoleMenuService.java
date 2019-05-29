package com.grass.admin.service;

import com.grass.admin.model.RoleMenu;

import java.util.List;

public interface RoleMenuService {

    List<RoleMenu> findListByRoleId(int roleId);

    List<RoleMenu> findListByRoleIds(List<Integer> roleIds);

    List<Integer> findMenuIdsByRoleId(int roleId);

    List<Integer> findMenuIdsByRoleIds(List<Integer> roleIds);

}
