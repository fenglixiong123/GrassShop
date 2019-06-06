package com.grass.admin.service.core;

import com.grass.admin.service.BaseService;
import com.grass.api.vo.admin.RoleVo;

import java.util.List;

public interface RoleService extends BaseService<RoleVo,Integer> {

    List<RoleVo> findListByAdminId(Long adminId);

    int deleteRoles(List<Integer> ids);

}
