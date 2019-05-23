package com.grass.console.service.impl;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.common.utils.CodeUtils;
import com.grass.console.common.constants.WebConstant;
import com.grass.console.service.ConsoleAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Fenglixiong
 * @Create 2019/5/22 23:31
 * @Description
 **/
@Slf4j
@Service
public class ConsoleAdminServiceImpl implements ConsoleAdminService {

    @Autowired
    private IAdminService adminService;

    @Override
    public String loginByUsername(HttpServletRequest request,String username, String password) {
        AdminVo adminVo = adminService.getAdminByUsernameAndPassword(username, password);
        if(adminVo!=null){
            String token = CodeUtils.getRandomCode(16);
            HttpSession session = request.getSession();
            session.setAttribute(WebConstant.USER_TOKEN,token);
            session.setAttribute(WebConstant.USER_INFO,adminVo);
            return token;
        }
        return null;
    }

    @Override
    public AdminVo findUserInfoByToken(HttpServletRequest request,String token) {
        HttpSession session = request.getSession();
        String s_token = (String)session.getAttribute(WebConstant.USER_TOKEN);
        if(s_token==null||!s_token.equals(token)){
            return null;
        }
        return (AdminVo)session.getAttribute(WebConstant.USER_INFO);
    }

    @Override
    public void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(WebConstant.USER_TOKEN);
        session.removeAttribute(WebConstant.USER_INFO);
    }
}
