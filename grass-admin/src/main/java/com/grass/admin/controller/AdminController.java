package com.grass.admin.controller;

import com.grass.admin.service.AdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:34
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public AdminVo getAdmin(@PathVariable("id") Long id){
        log.info("getAdmin---->id:{}",id);
        return adminService.getAdmin(id);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public PageResult<AdminVo> listAdmin(@RequestParam Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("listAdmin---->page:{},pageSize:{}",page,pageSize);
        PageQuery<AdminVo> pageQuery = new PageQuery<>(page,pageSize);
        return adminService.listAdmin(pageQuery);
    }

}
