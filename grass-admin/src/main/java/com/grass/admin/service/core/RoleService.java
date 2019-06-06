package com.grass.admin.service.core;

import com.grass.admin.service.BaseService;
import com.grass.api.vo.admin.PossessMenu;
import com.grass.api.vo.admin.PossessPower;
import com.grass.api.vo.admin.RoleVo;

import java.util.List;

public interface RoleService extends BaseService<RoleVo,Integer> {

    /**
     * 根据AdminId查询角色列表
     */
    List<RoleVo> findListByAdminId(Long adminId);


    /**
     * 根据角色ID获得拥有的菜单列表
     */
    PossessMenu findPossessMenuByRoleId(Integer roleId);

    /**
     * 根据角色ID获得拥有的权限列表
     */
    PossessPower findPossessPowerByRoleId(Integer roleId);

    /**
     * 分配菜单给指定角色
     */
    void assignMenuToRole(List<Integer> menuIds,Integer roleId);

    /**
     * 分配权限给指定角色
     */
    void assignPowerToRole(List<Integer> powerIds,Integer roleId);

    /**
     * 批量删除角色
     */
    int deleteRoles(List<Integer> ids);

}
