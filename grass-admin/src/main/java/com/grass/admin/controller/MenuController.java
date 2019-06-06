package com.grass.admin.controller;

import com.grass.admin.service.core.MenuService;
import com.grass.admin.utils.ConvertTreeUtil;
import com.grass.api.vo.admin.MenuVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.utils.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     * 根据ID查询菜单
     */
    @GetMapping("/{id}")
    public MenuVo get(@PathVariable("id") Integer id){
        log.info("get---->id:{}",id);
        return menuService.get(id);
    }

    /**
     * 新增菜单
     */
    @Valid
    @PostMapping
    public Integer add(@RequestBody MenuVo menuVo){
        log.info("add---->menuVo:{}", JsonUtils.toJsonMsg(menuVo));
        return menuService.add(menuVo);
    }

    /**
     * 更新菜单
     */
    @PutMapping
    public int update(@RequestBody MenuVo menuVo){
        log.info("update---->menuVo:{}",JsonUtils.toJsonMsg(menuVo));
        return menuService.update(menuVo);
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Integer id){
        log.info("delete---->id:{}",id);
        return menuService.delete(id);
    }

    /**
     * 分页查询菜单
     */
    @PostMapping("/list")
    public PageResult<MenuVo> list(@RequestBody(required = false) PageQuery<MenuVo> pageQuery){
        log.info("list---->pageQuery:{}",JsonUtils.toJsonMsg(pageQuery));
        if(pageQuery==null){
            pageQuery = new PageQuery<>();
        }
        return menuService.list(pageQuery);
    }
    
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
