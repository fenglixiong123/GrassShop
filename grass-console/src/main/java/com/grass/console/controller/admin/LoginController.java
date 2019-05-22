package com.grass.console.controller.admin;

import com.grass.api.service.admin.IAdminService;
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
    public ResultResponse loginByUsername(@RequestBody LoginFormVo loginFormVo){
        log.info("login---->loginForm:{}", JsonUtils.toJsonMsg(loginFormVo));
        String token = consoleAdminService.loginByUsername(loginFormVo.getUsername(),loginFormVo.getPassword());
        if(StringUtils.isNotBlank(token)){
            log.info("登录成功，token:{}",token);
            return ResultResponse.ok(token);
        }
        log.info("登录失败！");
        return ResultResponse.error(ErrorMsgEnum.LOGIN_USERNAME_ERROR);
    }

}
