package com.grass.admin.service;

import com.grass.admin.model.RolePower;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:29
 * @Description
 **/
public interface RolePowerService {

    List<RolePower> getListByRoleId(int roleId);

    List<RolePower> getListByRoleIds(List<Integer> roleIds);

    List<Integer> getPowerIdsByRoleId(int roleId);

    List<Integer> getPowerIdsByRoleId(List<Integer> roleIds);

}
