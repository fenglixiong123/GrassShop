package com.grass.admin.controller;

import com.grass.admin.service.core.RoleService;
import com.grass.api.vo.admin.RoleVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.utils.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:12
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 根据ID查询角色
     */
    @GetMapping("/{id}")
    public RoleVo get(@PathVariable("id") Integer id){
        log.info("get---->id:{}",id);
        return roleService.get(id);
    }

    /**
     * 新增角色
     */
    @Valid
    @PostMapping
    public Integer add(@RequestBody RoleVo roleVo){
        log.info("add---->roleVo:{}", JsonUtils.toJsonMsg(roleVo));
        return roleService.add(roleVo);
    }

    /**
     * 更新角色
     */
    @PutMapping
    public int update(@RequestBody RoleVo roleVo){
        log.info("update---->roleVo:{}",JsonUtils.toJsonMsg(roleVo));
        return roleService.update(roleVo);
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Integer id){
        log.info("delete---->id:{}",id);
        return roleService.delete(id);
    }

    /**
     * 分页查询角色
     * @return
     */
    @PostMapping("/list")
    public PageResult<RoleVo> list(@RequestBody(required = false) PageQuery<RoleVo> pageQuery){
        log.info("list---->pageQuery:{}",JsonUtils.toJsonMsg(pageQuery));
        if(pageQuery==null){
            pageQuery = new PageQuery<>();
        }
        return roleService.list(pageQuery);
    }



}
