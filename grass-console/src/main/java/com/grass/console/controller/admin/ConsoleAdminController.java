package com.grass.console.controller.admin;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.result.ResultResponse;
import com.grass.common.utils.json.JsonUtils;
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
public class ConsoleAdminController {

    @Autowired
    private IAdminService adminService;

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    public ResultResponse get(@PathVariable("id") Long id){
        log.info("get---->id:{}",id);
        return ResultResponse.ok(adminService.get(id));
    }

    /**
     * 新增用户
     */
    @Valid
    @PostMapping
    public ResultResponse add(@RequestBody AdminVo adminVo){
        log.info("add---->adminVo:", JsonUtils.toJsonMsg(adminVo));
        return ResultResponse.ok(adminService.add(adminVo));
    }

    /**
     * 更新用户
     */
    @PutMapping
    public ResultResponse update(@RequestBody AdminVo adminVo){
        log.info("update---->adminVo:{}",JsonUtils.toJsonMsg(adminVo));
        return ResultResponse.ok(adminService.update(adminVo));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public ResultResponse delete(@PathVariable("id") Long id){
        log.info("delete---->id:{}",id);
        return ResultResponse.ok(adminService.delete(id));
    }

    /**
     * 分页查询用户
     * @return
     */
    @PostMapping("/list")
    public ResultResponse list(@RequestBody(required = false) PageQuery<AdminVo> pageQuery){
        log.info("list---->pageQuery:{}",JsonUtils.toJsonMsg(pageQuery));
        if(pageQuery==null){
            pageQuery = new PageQuery<>();
        }
        return ResultResponse.ok(adminService.list(pageQuery));
    }

}
