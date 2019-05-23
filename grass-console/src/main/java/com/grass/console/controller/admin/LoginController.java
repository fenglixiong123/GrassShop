package com.grass.console.controller.admin;

import com.grass.api.vo.admin.AdminVo;
import com.grass.common.enums.ErrorMsgEnum;
import com.grass.common.result.ResultResponse;
import com.grass.common.utils.json.JsonUtils;
import com.grass.console.service.ConsoleAdminService;
import com.grass.console.vo.LoginFormVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fenglixiong
 * @Create 2019/5/22 22:51
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/console/admin")
public class LoginController {

    @Autowired
    private ConsoleAdminService consoleAdminService;

    @PostMapping("/login")
    public ResultResponse loginByUsername(HttpServletRequest request, @RequestBody LoginFormVo loginFormVo){
        log.info("login---->loginForm:{}", JsonUtils.toJsonMsg(loginFormVo));
        String token = consoleAdminService.loginByUsername(request,loginFormVo.getUsername(),loginFormVo.getPassword());
        if(StringUtils.isNotBlank(token)){
            log.info("登录成功，token:{}",token);
            return ResultResponse.ok(token);
        }
        log.info("登录失败！");
        return ResultResponse.error(ErrorMsgEnum.LOGIN_USERNAME_ERROR);
    }

    @GetMapping("/userInfo")
    public ResultResponse getUserInfo(HttpServletRequest request,@RequestParam String token){
        log.info("userInfo------->token:{}",token);
        AdminVo userInfo = consoleAdminService.findUserInfoByToken(request, token);
        if(userInfo!=null) {
            return ResultResponse.ok(userInfo);
        }else {
            return ResultResponse.error("Token已经过期！");
        }
    }

    @PostMapping("/logout")
    public ResultResponse logOut(HttpServletRequest request){
        log.info("logOut------------>");
        consoleAdminService.logOut(request);
        return ResultResponse.ok("登出成功");
    }

}
