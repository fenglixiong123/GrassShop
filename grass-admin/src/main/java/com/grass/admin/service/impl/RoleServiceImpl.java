package com.grass.admin.service.impl;

import com.grass.admin.dao.RoleDao;
import com.grass.admin.model.RoleExample;
import com.grass.admin.service.AdminRoleService;
import com.grass.admin.service.RoleService;
import com.grass.admin.utils.CopyUtil;
import com.grass.api.vo.admin.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:14
 * @Description
 **/
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RoleDao roleDao;

    /**
     * 获取所有的角色列表
     * @return
     */
    public List<RoleVo> findList(){
        return CopyUtil.copyRoleEntity(roleDao.selectByExample(null));
    }

    /**
     * 根据adminId获取角色列表
     * @param adminId
     * @return
     */
    public List<RoleVo> findListByAdminId(Long adminId){
        List<Integer> roleIds = adminRoleService.findRoleIdsByAdminId(adminId);
        RoleExample example = new RoleExample();
        example.createCriteria().andIdIn(roleIds);
        return CopyUtil.copyRoleEntity(roleDao.selectByExample(example));
    }

}
