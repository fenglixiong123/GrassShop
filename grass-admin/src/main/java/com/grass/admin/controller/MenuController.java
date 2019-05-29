package com.grass.admin.controller;

import com.grass.admin.service.MenuService;
import com.grass.admin.utils.ConvertTreeUtil;
import com.grass.api.vo.admin.MenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/29 22:59
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/findMenuListByAdminId")
    public List<MenuVo> findMenuListByAdminId(@RequestParam("id") Long id){
        log.info("findMenuListByAdminId---------->id:{}",id);
        return menuService.findListByAdminId(id);
    }

    @GetMapping("/findMenuTreeByAdminId")
    public List<MenuVo> findMenuTreeByAdminId(@RequestParam("id") Long id){
        log.info("findMenuTreeByAdminId---------->id:{}",id);
        return ConvertTreeUtil.listToTreeMenu(menuService.findListByAdminId(id));
    }
    
}
