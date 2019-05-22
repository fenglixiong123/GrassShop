package com.grass.admin.controller;

import com.grass.admin.service.AdminService;
import com.grass.api.vo.admin.AdminVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fenglixiong
 * @Create 2019/5/22 22:34
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/getAdminByUsernameAndPassword")
    public AdminVo getAdminByUsernameAndPassword(@RequestParam String username,
                                                 @RequestParam String password){
        log.info("getAdminByUsernameAndPassword----------->:username:{},password:{}",username,password);
        return adminService.getAdminByUsernameAndPassword(username,password);
    }

}
