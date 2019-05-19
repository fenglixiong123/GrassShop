package com.grass.admin.controller;

import com.grass.web.annotation.RequestLimit;
import com.grass.web.annotation.SysLog;
import com.grass.web.enums.BizTypeEnum;
import com.grass.web.enums.OperateTypeEnum;
import com.grass.common.result.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fenglixiong
 * @Create 2019/5/18 14:47
 * @Description
 **/
@RestController
@RequestMapping("/home")
public class HomeController {

    @RequestLimit(count = 2000)
    @SysLog(bizType = BizTypeEnum.SYSTEM,operateType = OperateTypeEnum.ADD,bizId = "id")
    @GetMapping("/hello")
    public ResultResponse<String> hello(){
        return ResultResponse.ok();
    }

}
