package com.grass.admin.service.impl;

import com.grass.admin.dao.PowerDao;
import com.grass.admin.model.AdminRole;
import com.grass.admin.model.Power;
import com.grass.admin.model.PowerExample;
import com.grass.admin.service.AdminRoleService;
import com.grass.admin.service.PowerService;
import com.grass.admin.service.RolePowerService;
import com.grass.api.vo.power.PowerVo;
import com.grass.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:15
 * @Description
 **/
@Slf4j
@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RolePowerService rolePowerService;
    @Autowired
    private PowerDao powerDao;

    public List<PowerVo> getListByPowerIds(List<Integer> powIds){
        if(CommonUtils.isEmpty(powIds)){
            return null;
        }
        PowerExample example = new PowerExample();
        example.createCriteria().andIdIn(powIds);
        List<Power> powers = powerDao.selectByExample(example);
        List<PowerVo> powerVos = new ArrayList<>();
        for (Power power : powers){
            PowerVo powerVo = new PowerVo();
            BeanUtils.copyProperties(power,powerVo);
            powerVos.add(powerVo);
        }
        return powerVos;
    }

    public List<PowerVo> getPowerListByAdminId(Long adminId){
        //1.首先获得用户角色ids
        List<Integer> roleIds = adminRoleService.getRoleIdsByAdminId(adminId);
        //2.获得用户权限ids
        List<Integer> powerIds = rolePowerService.getPowerIdsByRoleId(roleIds);
        //3.获取用户权限
        return getListByPowerIds(powerIds);
    }

}
