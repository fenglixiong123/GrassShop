package com.grass.console.controller.admin;

import com.grass.api.service.admin.IAdminService;
import com.grass.common.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fenglixiong
 * @Create 2019/5/30 0:21
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/console/admin")
public class ConsoleMenuController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/findMenuListByAdminId")
    public ResultResponse findMenuListByAdminId(@RequestParam Long id){
        log.info("findMenuListByAdminId------------->id:{}",id);
        return ResultResponse.ok(adminService.findMenuListByAdminId(id));
    }

    @GetMapping("/findMenuTreeByAdminId")
    public ResultResponse findMenuTreeByAdminId(@RequestParam Long id){
        log.info("findMenuTreeByAdminId------------->id:{}",id);
        return ResultResponse.ok(adminService.findMenuTreeByAdminId(id));
    }


}
