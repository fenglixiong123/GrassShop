package com.grass.console.controller.admin;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.MenuVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Fenglixiong
 * @Create 2019/5/30 0:21
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/console/admin/menu")
@Api(value = "控制台菜单相关")
public class ConsoleMenuController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询菜单")
    public ResultResponse<MenuVo> getMenu(@PathVariable("id") Integer id){
        log.info("getMenu------>");
        return ResultResponse.ok(adminService.getMenu(id));
    }

    @PostMapping
    @ApiOperation(value = "新增菜单")
    public ResultResponse<Integer> addMenu(@Valid @RequestBody MenuVo menuVo){
        log.info("addMenu------>");
        return ResultResponse.ok(adminService.addMenu(menuVo));
    }

    @PutMapping
    @ApiOperation(value = "修改菜单")
    public ResultResponse<Integer> updateMenu(@RequestBody MenuVo menuVo){
        log.info("updateMenu------>");
        return ResultResponse.ok(adminService.updateMenu(menuVo));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单")
    public ResultResponse<Integer> deleteMenu(@PathVariable("id") Integer id){
        log.info("deleteMenu------>");
        return ResultResponse.ok(adminService.deleteMenu(id));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页查询菜单")
    public ResultResponse<PageResult<MenuVo>> listPageMenu(@RequestBody(required = false) PageQuery<MenuVo> pageQuery){
        log.info("listPageMenu------>");
        return ResultResponse.ok(adminService.listPageMenu(pageQuery));
    }
    
    @GetMapping("/findMenuListByAdminId")
    @ApiOperation(value = "根据AdminId查询菜单列表")
    public ResultResponse findMenuListByAdminId(@RequestParam Long id){
        log.info("findMenuListByAdminId------------->id:{}",id);
        return ResultResponse.ok(adminService.findMenuListByAdminId(id));
    }

    @GetMapping("/findMenuTreeByAdminId")
    @ApiOperation(value = "根据AdminId查询菜单树")
    public ResultResponse findMenuTreeByAdminId(@RequestParam Long id){
        log.info("findMenuTreeByAdminId------------->id:{}",id);
        return ResultResponse.ok(adminService.findMenuTreeByAdminId(id));
    }


}
