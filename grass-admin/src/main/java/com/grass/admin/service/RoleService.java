package com.grass.admin.service;

import com.grass.api.vo.admin.RoleVo;

import java.util.List;

public interface RoleService extends BaseService<RoleVo,Integer>{

    List<RoleVo> findList();

    List<RoleVo> findListByAdminId(Long adminId);

}
