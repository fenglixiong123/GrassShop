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

    public List<RolePower> getListByRoleId(int roleId){
        RolePowerExample example = new RolePowerExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return rolePowerDao.selectByExample(example);
    }

    public List<RolePower> getListByRoleIds(List<Integer> roleIds){
        RolePowerExample example = new RolePowerExample();
        example.createCriteria().andRoleIdIn(roleIds);
        return rolePowerDao.selectByExample(example);
    }

    public List<Integer> getPowerIdsByRoleId(int roleId){
        List<RolePower> rolePowers = getListByRoleId(roleId);
        List<Integer> powerIds = new ArrayList<>();
        for (RolePower rolePower:rolePowers){
            powerIds.add(rolePower.getPowerId());
        }
        return powerIds;
    }

    public List<Integer> getPowerIdsByRoleId(List<Integer> roleIds){
        if(CommonUtils.isEmpty(roleIds)){
            return null;
        }
        List<RolePower> rolePowers = getListByRoleIds(roleIds);
        List<Integer> powerIds = new ArrayList<>();
        for (RolePower rolePower:rolePowers){
            powerIds.add(rolePower.getPowerId());
        }
        return powerIds;
    }

}
