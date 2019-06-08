package com.grass.console.service;

import com.grass.api.vo.admin.LoginResult;

import javax.servlet.http.HttpServletRequest;

public interface ConsoleAdminService {

    LoginResult loginByUsername(HttpServletRequest request, String username, String password);

    Object getUserInfo(HttpServletRequest request);

    void logOut(HttpServletRequest request);

}
