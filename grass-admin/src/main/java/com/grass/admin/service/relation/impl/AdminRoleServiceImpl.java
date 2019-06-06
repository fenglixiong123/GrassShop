package com.grass.admin.service.relation.impl;

import com.grass.admin.dao.AdminRoleDao;
import com.grass.admin.model.AdminRole;
import com.grass.admin.model.AdminRoleExample;
import com.grass.admin.service.relation.AdminRoleService;
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

    /**
     * 新增
     * @param adminRole
     * @return
     */
    public Integer add(AdminRole adminRole){
        return adminRoleDao.insertSelective(adminRole);
    }

    /**
     * 根据adminId删除用户角色关系
     * @param id
     * @return
     */
    public int deleteByAdminId(Long id){
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andAdminIdEqualTo(id);
        return adminRoleDao.deleteByExample(example);
    }

    /**
     * 通过adminIDs删除角色用户信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByAdminIds(List<Long> ids) {
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andAdminIdIn(ids);
        return adminRoleDao.deleteByExample(example);
    }

    /**
     * 根据roleId删除用户角色关系
     * @param id
     * @return
     */
    public int deleteByRoleId(Integer id){
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andRoleIdEqualTo(id);
        return adminRoleDao.deleteByExample(example);
    }

    /**
     * 根据roleIds批量删除角色关系
     * @param ids
     * @return
     */
    public int deleteByRoleIds(List<Integer> ids){
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andRoleIdIn(ids);
        return adminRoleDao.deleteByExample(example);
    }

    /**
     * 通过用户和角色共同删除直接的关系
     * @param adminId
     * @param roleIds
     * @return
     */
    @Override
    public int deleteByAdminAndRoles(Long adminId, List<Integer> roleIds) {
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andAdminIdEqualTo(adminId)
                .andRoleIdIn(roleIds);
        return adminRoleDao.deleteByExample(example);
    }

}
