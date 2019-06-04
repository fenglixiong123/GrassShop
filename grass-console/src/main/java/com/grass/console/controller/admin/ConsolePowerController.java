package com.grass.console.controller.admin;

import com.grass.api.service.admin.IAdminService;
import com.grass.common.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "控制台权限相关")
public class ConsolePowerController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/findPowerListByAdminId")
    public ResultResponse findPowerListByAdminId(@RequestParam Long id){
        log.info("findPowerListByAdminId------------->id:{}",id);
        return ResultResponse.ok(adminService.findPowerListByAdminId(id));
    }

    @GetMapping("/findPowerTreeByAdminId")
    public ResultResponse findPowerTreeByAdminId(@RequestParam Long id){
        log.info("findPowerTreeByAdminId------------->id:{}",id);
        return ResultResponse.ok(adminService.findPowerTreeByAdminId(id));
    }

}
