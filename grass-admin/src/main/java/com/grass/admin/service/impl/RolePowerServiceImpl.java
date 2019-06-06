package com.grass.admin.service.impl;

import com.grass.admin.dao.RolePowerDao;
import com.grass.admin.model.RolePower;
import com.grass.admin.model.RolePowerExample;
import com.grass.admin.service.RolePowerService;
import com.grass.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:30
 * @Description
 **/
@Slf4j
@Service
public class RolePowerServiceImpl implements RolePowerService {

    @Autowired
    private RolePowerDao rolePowerDao;

    /**
     * 通过角色ID获取角色权限列表
     * @param roleId
     * @return
     */
    public List<RolePower> findListByRoleId(int roleId){
        RolePowerExample example = new RolePowerExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return rolePowerDao.selectByExample(example);
    }

    /**
     * 通过角色ID集合获得角色权限列表
     * @param roleIds
     * @return
     */
    public List<RolePower> findListByRoleIds(List<Integer> roleIds){
        RolePowerExample example = new RolePowerExample();
        example.createCriteria().andRoleIdIn(roleIds);
        return rolePowerDao.selectByExample(example);
    }

    /**
     * 通过角色ID获得权限列表ID
     * @param roleId
     * @return
     */
    public List<Integer> findPowerIdsByRoleId(int roleId){
        List<RolePower> rolePowers = findListByRoleId(roleId);
        List<Integer> powerIds = new ArrayList<>();
        for (RolePower rolePower:rolePowers){
            powerIds.add(rolePower.getPowerId());
        }
        return powerIds;
    }

    /**
     * 通过角色IDS获得权限列表ID
     * @param roleIds
     * @return
     */
    public List<Integer> findPowerIdsByRoleIds(List<Integer> roleIds){
        if(CommonUtils.isEmpty(roleIds)){
            return null;
        }
        List<RolePower> rolePowers = findListByRoleIds(roleIds);
        List<Integer> powerIds = new ArrayList<>();
        for (RolePower rolePower:rolePowers){
            powerIds.add(rolePower.getPowerId());
        }
        return powerIds;
    }

    @Override
    public Integer add(RolePower rolePower) {
        return rolePowerDao.insertSelective(rolePower);
    }

    @Override
    public int deleteByRoleId(Integer id) {
        RolePowerExample example = new RolePowerExample();
        example.createCriteria().andRoleIdEqualTo(id);
        return rolePowerDao.deleteByExample(example);
    }

    @Override
    public int deleteByRoleIds(List<Integer> ids) {
        RolePowerExample example = new RolePowerExample();
        example.createCriteria().andRoleIdIn(ids);
        return rolePowerDao.deleteByExample(example);
    }

    @Override
    public int deleteByPowerId(Integer id) {
        RolePowerExample example = new RolePowerExample();
        example.createCriteria().andPowerIdEqualTo(id);
        return rolePowerDao.deleteByExample(example);
    }

    @Override
    public int deleteByPowerIds(List<Integer> ids) {
        RolePowerExample example = new RolePowerExample();
        example.createCriteria().andPowerIdIn(ids);
        return rolePowerDao.deleteByExample(example);
    }


}
