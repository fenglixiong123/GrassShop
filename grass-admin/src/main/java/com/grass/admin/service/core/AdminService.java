package com.grass.admin.service.core;

import com.grass.admin.service.BaseService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.api.vo.admin.PossessRole;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;

import java.util.List;


public interface AdminService extends BaseService<AdminVo,Long> {

    /**
     * 通过用户名密码获取用户信息
     * @param username
     * @param password
     * @return
     */
    AdminVo getAdminByUsernameAndPassword(String username,String password);

    /**
     * 根据用户ID获取已经拥有的角色信息
     * @param adminId
     * @return
     */
    PossessRole findPossessRoleByAdminId(Long adminId);

    /**
     * 分配角色给指定用户
     * @param roleIds
     * @param adminId
     */
    void assignRoleToAdmin(List<Integer> roleIds, Long adminId);


}
