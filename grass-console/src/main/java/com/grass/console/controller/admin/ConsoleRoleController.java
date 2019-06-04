package com.grass.console.controller.admin;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.RoleVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Fenglixiong
 * @Create 2019/6/4 0:41
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/console/admin/role")
@Api(value = "控制台角色相关")
public class ConsoleRoleController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/{id}")
    public ResultResponse<RoleVo> get(@PathVariable("id") Integer id){
        log.info("getRole------>");
        return ResultResponse.ok(adminService.getRole(id));
    }

    @PostMapping
    public ResultResponse<Integer> add(@RequestBody RoleVo roleVo){
        log.info("addRole------>");
        return ResultResponse.ok(adminService.addRole(roleVo));
    }

    @PutMapping
    public ResultResponse<Integer> update(@RequestBody RoleVo roleVo){
        log.info("updateRole------>");
        return ResultResponse.ok(adminService.updateRole(roleVo));
    }

    @DeleteMapping("/{id}")
    public ResultResponse<Integer> delete(@PathVariable("id") Integer id){
        log.info("deleteRole------>");
        return ResultResponse.ok(adminService.deleteRole(id));
    }

    @PostMapping("/list")
    public ResultResponse<PageResult<RoleVo>> listPage(@RequestBody(required = false) PageQuery<RoleVo> pageQuery){
        log.info("listPageRole------>");
        return ResultResponse.ok(adminService.listPageRole(pageQuery));
    }


}
