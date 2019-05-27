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

    public List<AdminRole> getListByAdminId(Long adminId){
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        return adminRoleDao.selectByExample(example);
    }

    public List<AdminRole> getListByAdminIds(List<Long> adminIds){
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andAdminIdIn(adminIds);
        return adminRoleDao.selectByExample(example);
    }

    public List<Integer> getRoleIdsByAdminId(Long adminId){
        List<AdminRole> adminRoles = getListByAdminId(adminId);
        List<Integer> roleIds = new ArrayList<>();
        for(AdminRole adminRole:adminRoles){
            roleIds.add(adminRole.getRoleId());
        }
        return roleIds;
    }

    public List<Integer> getRoleIdsByAdminIds(List<Long> adminIds){
        if(CommonUtils.isEmpty(adminIds)){
            return null;
        }
        List<AdminRole> adminRoles = getListByAdminIds(adminIds);
        List<Integer> roleIds = new ArrayList<>();
        for(AdminRole adminRole:adminRoles){
            roleIds.add(adminRole.getRoleId());
        }
        return roleIds;
    }

}
