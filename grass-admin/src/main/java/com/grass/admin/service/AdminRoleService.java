package com.grass.admin.service;

import com.grass.admin.model.AdminRole;

import java.util.List;

public interface AdminRoleService {

    List<AdminRole> getListByAdminId(Long adminId);

    List<AdminRole> getListByAdminIds(List<Long> adminIds);

    List<Integer> getRoleIdsByAdminId(Long adminId);

    List<Integer> getRoleIdsByAdminIds(List<Long> adminIds);

}
