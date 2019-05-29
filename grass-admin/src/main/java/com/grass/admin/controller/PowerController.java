package com.grass.admin.controller;

import com.grass.admin.service.PowerService;
import com.grass.admin.utils.ConvertTreeUtil;
import com.grass.api.vo.admin.PowerVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/findPowerListByAdminId")
    public List<PowerVo> findPowerListByAdminId(@RequestParam("id") Long id){
        log.info("findPowerListByAdminId---------->id:{}",id);
        return powerService.findListByAdminId(id);
    }

    @GetMapping("/findPowerByAdminId")
    public List<PowerVo> findPowerTreeByAdminId(@RequestParam("id") Long id){
        log.info("findPowerTreeByAdminId---------->id:{}",id);
        return ConvertTreeUtil.listToTreePower(powerService.findListByAdminId(id));
    }

}
