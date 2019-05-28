package com.grass.admin.service.impl;

import com.grass.admin.dao.AdminRoleDao;
import com.grass.admin.model.AdminRole;
import com.grass.admin.model.AdminRoleExample;
import com.grass.admin.service.AdminRoleService;
import com.grass.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:32
 * @Description
 **/
@Slf4j
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;

    /**
     * 通过adminID获取用户角色列表
     * @param adminId
     * @return
     */
    public List<AdminRole> findListByAdminId(Long adminId){
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        return adminRoleDao.selectByExample(example);
    }

    /**
     * 通过用户ID获取角色列表ids
     * @param adminId
     * @return
     */
    public List<Integer> findRoleIdsByAdminId(Long adminId){
        List<AdminRole> adminRoles = findListByAdminId(adminId);
        List<Integer> roleIds = new ArrayList<>();
        for(AdminRole adminRole:adminRoles){
            roleIds.add(adminRole.getRoleId());
        }
        return roleIds;
    }

}
