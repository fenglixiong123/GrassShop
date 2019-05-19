package com.grass.gateway.controller;

import com.grass.common.result.ResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 12:07
 * @Description
 **/
@RestController
public class AdminFallbackController {

    @RequestMapping("/admin/fallback")
    public ResultResponse adminFallback(){
        return ResultResponse.ok("admin fallback");
    }

}
