package com.grass.admin.service;

import com.grass.admin.model.RolePower;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:29
 * @Description
 **/
public interface RolePowerService {

    List<RolePower> findListByRoleId(int roleId);

    List<RolePower> findListByRoleIds(List<Integer> roleIds);

    List<Integer> findPowerIdsByRoleId(int roleId);

    List<Integer> findPowerIdsByRoleIds(List<Integer> roleIds);

}
