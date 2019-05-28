package com.grass.admin.service;

import com.grass.api.vo.power.PowerVo;

import java.util.List;

public interface PowerService {

    List<PowerVo> findList();

    List<PowerVo> findListByAdminId(Long adminId);

    List<PowerVo> findListByRoleId(Integer roleId);

    List<PowerVo> findListByRoleIds(List<Integer> roleIds);

    List<PowerVo> findListByPowerIds(List<Integer> powIds);
}
