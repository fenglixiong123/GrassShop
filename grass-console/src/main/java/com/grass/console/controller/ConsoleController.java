package com.grass.console.controller;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.web.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:44
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping(value = "/admin/get/{id}",method = RequestMethod.GET)
    public ResultResponse<AdminVo> getAdmin(@PathVariable("id") Long id){
        log.info("getAdmin---->id:{}",id);
        return ResultResponse.ok(adminService.getAdmin(id));
    }

    @RequestMapping(value = "/admin/list",method = RequestMethod.GET)
    public ResultResponse<List<AdminVo>> listAdmin(){
        log.info("listAdmin---->");
        return ResultResponse.ok(adminService.listAdmin());
    }

}
