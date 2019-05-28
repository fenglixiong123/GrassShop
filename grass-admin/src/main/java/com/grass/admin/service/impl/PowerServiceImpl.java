package com.grass.admin.service.impl;

import com.grass.admin.dao.PowerDao;
import com.grass.admin.model.Power;
import com.grass.admin.model.PowerExample;
import com.grass.admin.service.AdminRoleService;
import com.grass.admin.service.PowerService;
import com.grass.admin.service.RolePowerService;
import com.grass.admin.utils.CopyUtil;
import com.grass.api.vo.power.PowerVo;
import com.grass.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 获取所有权限列表
     * @return
     */
    public List<PowerVo> findList(){
        List<Power> powers = powerDao.selectByExample(null);
        return CopyUtil.copyPowerEntity(powers);
    }

    /**
     * 通过adminId获取权限列表
     * @param adminId
     * @return
     */
    public List<PowerVo> findListByAdminId(Long adminId){
        //1.首先获得用户角色ids
        List<Integer> roleIds = adminRoleService.findRoleIdsByAdminId(adminId);
        //2.获得用户权限ids
        List<Integer> powerIds = rolePowerService.findPowerIdsByRoleIds(roleIds);
        //3.获取用户权限
        return findListByPowerIds(powerIds);
    }

    /**
     * 通过roleId获取权限列表
     * @param roleId
     * @return
     */
    public List<PowerVo> findListByRoleId(Integer roleId){
        List<Integer> powerIds = rolePowerService.findPowerIdsByRoleId(roleId);
        return findListByPowerIds(powerIds);
    }

    /**
     * 通过roleIds获取权限列表
     * @param roleIds
     * @return
     */
    public List<PowerVo> findListByRoleIds(List<Integer> roleIds){
        List<Integer> powerIds = rolePowerService.findPowerIdsByRoleIds(roleIds);
        return findListByPowerIds(powerIds);
    }

    /**
     * 通过powerIds获取权限列表
     * @param powIds
     * @return
     */
    public List<PowerVo> findListByPowerIds(List<Integer> powIds){
        if(CommonUtils.isEmpty(powIds)){
            return null;
        }
        PowerExample example = new PowerExample();
        example.createCriteria().andIdIn(powIds);
        List<Power> powers = powerDao.selectByExample(example);
        return CopyUtil.copyPowerEntity(powers);
    }



}
