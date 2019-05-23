package com.grass.console.service;

import com.grass.api.vo.admin.AdminVo;

import javax.servlet.http.HttpServletRequest;

public interface ConsoleAdminService {

    String loginByUsername(HttpServletRequest request,String username, String password);

    AdminVo findUserInfoByToken(HttpServletRequest request,String token);

    void logOut(HttpServletRequest request);

}
