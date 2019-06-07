package com.grass.console.controller.admin;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.PossessMenu;
import com.grass.api.vo.admin.PossessPower;
import com.grass.api.vo.admin.RoleVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.result.ResultResponse;
import com.grass.common.utils.json.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @ApiOperation(value = "根据ID查询角色")
    public ResultResponse<RoleVo> getRole(@PathVariable("id") Integer id){
        log.info("getRole------>id:{}",id);
        return ResultResponse.ok(adminService.getRole(id));
    }

    @PostMapping
    @ApiOperation(value = "新增角色")
    public ResultResponse<Integer> addRole(@Valid @RequestBody RoleVo roleVo){
        log.info("addRole------>roleVo:{}", JsonUtils.toJsonMsg(roleVo));
        return ResultResponse.ok(adminService.addRole(roleVo));
    }

    @PutMapping
    @ApiOperation(value = "修改角色")
    public ResultResponse<Integer> updateRole(@RequestBody RoleVo roleVo){
        log.info("updateRole------>roleVo:{}",JsonUtils.toJsonMsg(roleVo));
        return ResultResponse.ok(adminService.updateRole(roleVo));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    public ResultResponse<Integer> deleteRole(@PathVariable("id") Integer id){
        log.info("deleteRole------>id:{}",id);
        return ResultResponse.ok(adminService.deleteRole(id));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页查询角色")
    public ResultResponse<PageResult<RoleVo>> listPageRole(@RequestBody(required = false) PageQuery<RoleVo> pageQuery){
        log.info("listPageRole------>pageQuery:{}",JsonUtils.toJsonMsg(pageQuery));
        return ResultResponse.ok(adminService.listPageRole(pageQuery));
    }

    @GetMapping("/findPossessMenuByRoleId/{id}")
    @ApiOperation(value = "通过角色ID查询拥有的菜单树形")
    public ResultResponse<PossessMenu> findPossessMenuByRoleId(@PathVariable Integer id){
        log.info("findPossessMenuByRoleId---->id:{}",id);
        return ResultResponse.ok(adminService.findPossessMenuByRoleId(id));
    }

    @PostMapping("/assignMenuToRole")
    @ApiOperation(value = "给指定角色分配菜单")
    public ResultResponse assignMenuToRole(@RequestParam("menuIds") List<Integer> menuIds,
                                           @RequestParam("roleId") Integer roleId){
        log.info("assignMenuToRole------->roleId:{},menuIds:{}",roleId,menuIds);
        adminService.assignMenuToRole(menuIds,roleId);
        return ResultResponse.ok();
    }

    @GetMapping("/findPossessPowerByRoleId/{id}")
    @ApiOperation(value = "通过角色ID查询拥有的权限树形")
    public ResultResponse<PossessPower> findPossessPowerByRoleId(@PathVariable Integer id){
        log.info("findPossessPowerByRoleId---->id:{}",id);
        return ResultResponse.ok(adminService.findPossessPowerByRoleId(id));
    }

    @PostMapping("/assignPowerToRole")
    @ApiOperation(value = "给指定角色分配权限")
    public ResultResponse assignPowerToRole(@RequestParam("powerIds") List<Integer> powerIds,
                                            @RequestParam Integer roleId){
        log.info("assignPowerToRole------->roleId:{},powerIds:{}",roleId,powerIds);
        adminService.assignPowerToRole(powerIds,roleId);
        return ResultResponse.ok();
    }


}
