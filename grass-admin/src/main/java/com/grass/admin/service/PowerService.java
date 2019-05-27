package com.grass.admin.service;

import com.grass.api.vo.power.PowerVo;

import java.util.List;

public interface PowerService {

    List<PowerVo> getListByPowerIds(List<Integer> powIds);

    List<PowerVo> getPowerListByAdminId(Long adminId);

}
