package com.grass.admin.service;

import com.grass.api.vo.role.RoleVo;

import java.util.List;

public interface RoleService {

    List<RoleVo> findList();

    List<RoleVo> findListByAdminId(Long adminId);

}
