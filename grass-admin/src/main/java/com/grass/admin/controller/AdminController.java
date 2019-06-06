package com.grass.admin.controller;

import com.grass.admin.service.core.AdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.utils.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    public AdminVo get(@PathVariable("id") Long id){
        log.info("get---->id:{}",id);
        return adminService.get(id);
    }

    /**
     * 新增用户
     */
    @Valid
    @PostMapping
    public Long add(@RequestBody AdminVo adminVo){
        log.info("add---->adminVo:{}", JsonUtils.toJsonMsg(adminVo));
        return adminService.add(adminVo);
    }

    /**
     * 更新用户
     */
    @PutMapping
    public int update(@RequestBody AdminVo adminVo){
        log.info("update---->adminVo:{}",JsonUtils.toJsonMsg(adminVo));
        return adminService.update(adminVo);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Long id){
        log.info("delete---->id:{}",id);
        return adminService.delete(id);
    }

    /**
     * 分页查询用户
     * @return
     */
    @PostMapping("/list")
    public PageResult<AdminVo> list(@RequestBody(required = false) PageQuery<AdminVo> pageQuery){
        log.info("list---->pageQuery:{}",JsonUtils.toJsonMsg(pageQuery));
        if(pageQuery==null){
            pageQuery = new PageQuery<>();
        }
        return adminService.list(pageQuery);
    }

}
