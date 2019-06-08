package com.grass.console.controller.admin;

import com.grass.api.vo.admin.LoginResult;
import com.grass.common.result.ResultResponse;
import com.grass.common.utils.json.JsonUtils;
import com.grass.console.service.ConsoleAdminService;
import com.grass.console.vo.LoginFormVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author Fenglixiong
 * @Create 2019/5/22 22:51
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/console/admin")
@Api("控制台登录相关")
public class ConsoleLoginController {

    @Autowired
    private ConsoleAdminService consoleAdminService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResultResponse<LoginResult> loginByUsername(HttpServletRequest request, @Valid @RequestBody LoginFormVo loginFormVo){
        log.info("login---->loginForm:{}", JsonUtils.toJsonMsg(loginFormVo));
        return ResultResponse.ok(consoleAdminService.loginByUsername(request,loginFormVo.getUsername(),loginFormVo.getPassword()));

    }

    @GetMapping("/userInfo")
    @ApiOperation("获取用户信息")
    public ResultResponse getUserInfo(HttpServletRequest request){
        log.info("userInfo------->");
        return ResultResponse.ok(consoleAdminService.getUserInfo(request));

    }

    @PostMapping("/logout")
    @ApiOperation("用户退出")
    public ResultResponse logOut(HttpServletRequest request){
        log.info("logOut------------>");
        consoleAdminService.logOut(request);
        return ResultResponse.ok("登出成功");
    }

}
