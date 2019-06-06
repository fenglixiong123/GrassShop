package com.grass.console.controller.admin;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.api.vo.admin.PossessRole;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:44
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/console/admin")
@Api(value = "控制台管理员相关")
public class ConsoleAdminController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询用户")
    public ResultResponse get(@PathVariable("id") Long id){
        log.info("get---->id:{}",id);
        return ResultResponse.ok(adminService.getAdmin(id));
    }


    @Valid
    @PostMapping
    @ApiOperation(value = "新增用户")
    public ResultResponse add(@Valid @RequestBody AdminVo adminVo){
        log.info("add---->adminVo:{}", JsonUtils.toJsonMsg(adminVo));
        return ResultResponse.ok(adminService.addAdmin(adminVo));
    }


    @PutMapping
    @ApiOperation(value = "更新用户")
    public ResultResponse update(@RequestBody AdminVo adminVo){
        log.info("update---->adminVo:{}",JsonUtils.toJsonMsg(adminVo));
        return ResultResponse.ok(adminService.updateAdmin(adminVo));
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    public ResultResponse delete(@PathVariable("id") Long id){
        log.info("delete---->id:{}",id);
        return ResultResponse.ok(adminService.deleteAdmin(id));
    }


    @PostMapping("/list")
    @ApiOperation(value = "分页查询用户")
    public ResultResponse list(@RequestBody(required = false) PageQuery<AdminVo> pageQuery){
        log.info("list---->pageQuery:{}",JsonUtils.toJsonMsg(pageQuery));
        if(pageQuery==null){
            pageQuery = new PageQuery<>();
        }
        return ResultResponse.ok(adminService.listPageAdmin(pageQuery));
    }

    @ApiOperation(value = "根据用户ID查询拥有的角色")
    @GetMapping("/findPossessRoleByAdminId/{id}")
    public ResultResponse<PossessRole> findPossessRoleByAdminId(@PathVariable("id") Long id){
        log.info("findPossessRoleByAdminId----->id:{}",id);
        return ResultResponse.ok(adminService.findPossessRoleByAdminId(id));
    }

    @ApiOperation(value = "分配角色给用户")
    @PostMapping("/assignRoleToAdmin")
    public ResultResponse assignRoleToAdmin(@RequestParam("roleIds") List<Integer> roleIds,
                                            @RequestParam("adminId") Long adminId){
        log.info("assignRoleToAdmin----->adminId:{},roleIds:{}",adminId,roleIds);
        adminService.assignRoleToAdmin(roleIds,adminId);
        return ResultResponse.ok();
    }


}
