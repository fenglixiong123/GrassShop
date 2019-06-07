package com.grass.console.controller.admin;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.PowerVo;
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
 * @Create 2019/5/28 23:56
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/console/admin/power")
@Api(value = "控制台权限相关")
public class ConsolePowerController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询权限")
    public ResultResponse<PowerVo> getPower(@PathVariable("id") Integer id){
        log.info("getPower------>id:{}",id);
        return ResultResponse.ok(adminService.getPower(id));
    }

    @PostMapping
    @ApiOperation(value = "新增权限")
    public ResultResponse<Integer> addPower(@Valid @RequestBody PowerVo powerVo){
        log.info("addPower------>powerVo:{}", JsonUtils.toJsonMsg(powerVo));
        return ResultResponse.ok(adminService.addPower(powerVo));
    }

    @PutMapping
    @ApiOperation(value = "修改权限")
    public ResultResponse<Integer> updatePower(@RequestBody PowerVo powerVo){
        log.info("updatePower------>powerVo:{}",JsonUtils.toJsonMsg(powerVo));
        return ResultResponse.ok(adminService.updatePower(powerVo));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除权限")
    public ResultResponse<Integer> deletePower(@PathVariable("id") Integer id){
        log.info("deletePower------>id:{}",id);
        return ResultResponse.ok(adminService.deletePower(id));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页查询权限")
    public ResultResponse<PageResult<PowerVo>> listPagePower(@RequestBody(required = false) PageQuery<PowerVo> pageQuery){
        log.info("listPagePower------>pageQuery:{}",JsonUtils.toJsonMsg(pageQuery));
        return ResultResponse.ok(adminService.listPagePower(pageQuery));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "树形查询权限")
    public ResultResponse<List<PowerVo>> treePower(){
        log.info("tree---------->");
        return ResultResponse.ok(adminService.treePower());
    }
    
    @GetMapping("/findPowerListByAdminId")
    @ApiOperation(value = "根据AdminId查询权限列表")
    public ResultResponse findPowerListByAdminId(@RequestParam Long id){
        log.info("findPowerListByAdminId------------->id:{}",id);
        return ResultResponse.ok(adminService.findPowerListByAdminId(id));
    }

    @GetMapping("/findPowerTreeByAdminId")
    @ApiOperation(value = "根据AdminId查询权限树")
    public ResultResponse findPowerTreeByAdminId(@RequestParam Long id){
        log.info("findPowerTreeByAdminId------------->id:{}",id);
        return ResultResponse.ok(adminService.findPowerTreeByAdminId(id));
    }

}
