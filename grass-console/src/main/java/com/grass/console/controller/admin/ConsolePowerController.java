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
 * @Create 2019/5/28 23:56
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/console/admin")
public class ConsolePowerController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/findPowerByAdminId")
    public ResultResponse listByAdminId(@RequestParam Long id){
        log.info("findPowerByAdminId------------->id:{}",id);
        return ResultResponse.ok(adminService.findPowerByAdminId(id));
    }

}
