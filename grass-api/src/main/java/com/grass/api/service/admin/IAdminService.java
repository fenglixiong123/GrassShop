package com.grass.api.service.admin;

import com.grass.api.service.admin.fallback.AdminServiceFallbackFactory;
import com.grass.api.vo.admin.AdminVo;
import com.grass.api.vo.power.PowerVo;
import com.grass.common.constants.AppConstant;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:01
 * @Description
 **/
@FeignClient(value = AppConstant.GRASS_ADMIN,fallbackFactory = AdminServiceFallbackFactory.class)
public interface IAdminService {

    @GetMapping("/admin/{id}")
    AdminVo get(@PathVariable("id") Long id);

    @PostMapping("/admin")
    Long add(@RequestBody AdminVo adminVo);

    @PutMapping("/admin")
    int update(@RequestBody AdminVo adminVo);

    @DeleteMapping("/admin/{id}")
    int delete(@PathVariable("id") Long id);

    @PostMapping("/admin/list")
    PageResult<AdminVo> list(@RequestBody(required = false) PageQuery<AdminVo> pageQuery);

    @PostMapping("/admin/getAdminByUsernameAndPassword")
    AdminVo getAdminByUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password);

    //---------------------------------------------------------------------------------------

    @GetMapping("/admin/power/findPowerByAdminId")
    List<PowerVo> findPowerByAdminId(@RequestParam("id") Long id);

}
