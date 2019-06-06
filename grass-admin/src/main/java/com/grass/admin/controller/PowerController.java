package com.grass.admin.controller;

import com.grass.admin.service.core.PowerService;
import com.grass.admin.utils.ConvertTreeUtil;
import com.grass.api.vo.admin.PowerVo;
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
 * @Create 2019/5/28 0:12
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping("/admin/power")
public class PowerController {

    @Autowired
    private PowerService powerService;

    /**
     * 根据ID查询权限
     */
    @GetMapping("/{id}")
    public PowerVo get(@PathVariable("id") Integer id){
        log.info("get---->id:{}",id);
        return powerService.get(id);
    }

    /**
     * 新增权限
     */
    @Valid
    @PostMapping
    public Integer add(@RequestBody PowerVo powerVo){
        log.info("add---->powerVo:{}", JsonUtils.toJsonMsg(powerVo));
        return powerService.add(powerVo);
    }

    /**
     * 更新权限
     */
    @PutMapping
    public int update(@RequestBody PowerVo powerVo){
        log.info("update---->powerVo:{}",JsonUtils.toJsonMsg(powerVo));
        return powerService.update(powerVo);
    }

    /**
     * 删除权限
     */
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Integer id){
        log.info("delete---->id:{}",id);
        return powerService.delete(id);
    }

    /**
     * 分页查询权限
     * @return
     */
    @PostMapping("/list")
    public PageResult<PowerVo> list(@RequestBody(required = false) PageQuery<PowerVo> pageQuery){
        log.info("list---->pageQuery:{}",JsonUtils.toJsonMsg(pageQuery));
        if(pageQuery==null){
            pageQuery = new PageQuery<>();
        }
        return powerService.list(pageQuery);
    }
    
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
