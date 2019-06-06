package com.grass.admin.service.relation;

import com.grass.admin.model.AdminRole;

import java.util.List;

public interface AdminRoleService {

    List<AdminRole> findListByAdminId(Long adminId);

    List<Integer> findRoleIdsByAdminId(Long adminId);

    Integer add(AdminRole adminRole);

    int deleteByAdminId(Long id);

    int deleteByAdminIds(List<Long> ids);

    int deleteByRoleId(Integer id);

    int deleteByRoleIds(List<Integer> ids);

    /**
     * 通过用户和角色共同删除直接的关系
     */
    int deleteByAdminAndRoles(Long adminId,List<Integer> roleIds);

}
