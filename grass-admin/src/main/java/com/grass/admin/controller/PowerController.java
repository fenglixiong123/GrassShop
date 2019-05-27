package com.grass.admin.controller;

import com.grass.admin.service.PowerService;
import com.grass.api.vo.power.PowerVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:12
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/admin/power")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @GetMapping("/listByAdmin")
    public List<PowerVo> listByAdmin(@RequestParam Long id){
        return powerService.getPowerListByAdminId(id);
    }

}
