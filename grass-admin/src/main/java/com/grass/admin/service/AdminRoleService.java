package com.grass.admin.service;

import com.grass.admin.model.AdminRole;

import java.util.List;

public interface AdminRoleService {

    List<AdminRole> findListByAdminId(Long adminId);

    List<Integer> findRoleIdsByAdminId(Long adminId);


}
