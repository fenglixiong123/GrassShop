package com.grass.admin.service;

import com.grass.api.vo.admin.AdminVo;

import java.util.List;

public interface AdminService {

    AdminVo getAdmin(Long id);

    List<AdminVo> listAdmin();

}
