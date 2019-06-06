package com.grass.admin.service.core;

import com.grass.admin.service.BaseService;
import com.grass.api.vo.admin.PossessMenu;
import com.grass.api.vo.admin.PossessPower;
import com.grass.api.vo.admin.RoleVo;

import java.util.List;

public interface RoleService extends BaseService<RoleVo,Integer> {

    /**
     * 根据AdminId查询角色列表
     * @param adminId
     * @return
     */
    List<RoleVo> findListByAdminId(Long adminId);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    int deleteRoles(List<Integer> ids);

    /**
     * 根据角色ID获得拥有的菜单列表
     * @param roleId
     * @return
     */
    PossessMenu findPossessMenuByRoleId(Integer roleId);

    /**
     * 根据角色ID获得拥有的权限列表
     * @param roleId
     * @return
     */
    PossessPower findPossessPowerByRoleId(Integer roleId);

}
