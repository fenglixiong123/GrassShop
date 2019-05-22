package com.grass.console.service.impl;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.common.utils.CodeUtils;
import com.grass.console.service.ConsoleAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    private Map<String,String> tokenMap = new HashMap<>();

    @Override
    public String loginByUsername(String username, String password) {
        AdminVo adminVo = adminService.getAdminByUsernameAndPassword(username, password);
        if(adminVo!=null){
            String token = CodeUtils.getRandomCode(16);
            tokenMap.put(username,token);
            return token;
        }
        return null;
    }
}
