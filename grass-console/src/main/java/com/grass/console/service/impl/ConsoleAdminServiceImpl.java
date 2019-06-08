package com.grass.console.service.impl;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.api.vo.admin.LoginResult;
import com.grass.api.vo.admin.MenuVo;
import com.grass.api.vo.admin.PowerVo;
import com.grass.common.utils.CodeUtils;
import com.grass.common.utils.CommonUtils;
import com.grass.console.common.constants.WebConstant;
import com.grass.console.service.ConsoleAdminService;
import com.grass.web.exception.element.BizException;
import com.grass.web.exception.element.UnAuthorizedException;
import com.grass.web.exception.element.UserLockedException;
import com.grass.web.exception.element.UserLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public LoginResult loginByUsername(HttpServletRequest request, String username, String password) {
        AdminVo adminVo = adminService.getAdminByUsernameAndPassword(username, password);
        if(adminVo==null){
            throw new UserLoginException("用户名或者密码错误！");
        }
        if(adminVo.getStatus()!=1){
            throw new UserLockedException("用户已被禁用请联系管理员处理！");
        }
        String token = CodeUtils.getStringCode(16);
        log.info("1.获取用户Token：{}",token);
        HttpSession session = request.getSession();
        session.setAttribute(WebConstant.USER_TOKEN,token);
        session.setAttribute(WebConstant.USER_INFO,adminVo);
        log.info("2.准备获取用户菜单...adminId:{}",adminVo.getId());
        List<MenuVo> menuVos = adminService.findMenuListByAdminId(adminVo.getId());
        if(CommonUtils.isEmpty(menuVos)){
            log.info("未获取到用户菜单！");
            throw new UserLoginException("未获取到用户菜单！");
        }
        log.info("完成获取用户菜单...数量：{}",menuVos.size());
        log.info("3.准备获取用户权限...adminId:{}",adminVo.getId());
        List<PowerVo> powerVos = adminService.findPowerListByAdminId(adminVo.getId());
        if(CommonUtils.isEmpty(powerVos)){
            log.info("未获取到用户权限！");
            throw new UserLoginException("未获取到用户权限！");
        }
        session.setAttribute(WebConstant.USER_POWER,powerVos);
        log.info("完成获取用户权限...数量：{}",powerVos.size());
        log.info("4.登录成功！");
        return new LoginResult(token,adminVo,menuVos);
    }

    @Override
    public Object getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object token = session.getAttribute(WebConstant.USER_TOKEN);
        if(token==null){
            throw new BizException("用户Token不存在");
        }
        return token;
    }

    @Override
    public void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(WebConstant.USER_TOKEN);
        session.removeAttribute(WebConstant.USER_INFO);
        session.invalidate();
    }
}
