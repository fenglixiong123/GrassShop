package com.grass.api.service.admin;

import com.grass.api.service.admin.fallback.AdminServiceFallbackFactory;
import com.grass.api.vo.admin.AdminVo;
import com.grass.api.vo.admin.MenuVo;
import com.grass.api.vo.admin.PowerVo;
import com.grass.api.vo.admin.RoleVo;
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

    //用户相关API------------------------------------------------------------------------------------------

    //根据adminId获取用户详情
    @GetMapping("/admin/{id}")
    AdminVo get(@PathVariable("id") Long id);

    //添加用户
    @PostMapping("/admin")
    Long add(@RequestBody AdminVo adminVo);

    //修改用户
    @PutMapping("/admin")
    int update(@RequestBody AdminVo adminVo);

    //删除用户
    @DeleteMapping("/admin/{id}")
    int delete(@PathVariable("id") Long id);

    //用户列表带分页
    @PostMapping("/admin/list")
    PageResult<AdminVo> list(@RequestBody(required = false) PageQuery<AdminVo> pageQuery);

    //根据用户名密码查询用户
    @PostMapping("/admin/getAdminByUsernameAndPassword")
    AdminVo getAdminByUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password);

    //角色相关API-----------------------------------------------------------------------------------------

    @GetMapping("/admin/role/{id}")
    RoleVo getRole(@PathVariable("id") Integer id);

    @PostMapping("/admin/role")
    Integer addRole(@RequestBody RoleVo roleVo);

    @PutMapping("/admin/role")
    int updateRole(@RequestBody RoleVo roleVo);

    @DeleteMapping("/admin/role/{id}")
    int deleteRole(@PathVariable("id") Integer id);

    @PostMapping("/admin/role/list")
    PageResult<RoleVo> listPageRole(@RequestBody(required = false) PageQuery<RoleVo> pageQuery);

    //菜单相关API---------------------------------------------------------------------------------------

    //权限相关API---------------------------------------------------------------------------------------

    //通过adminId获取菜单集合
    @GetMapping("/admin/menu/findMenuListByAdminId")
    List<MenuVo> findMenuListByAdminId(@RequestParam("id") Long id);

    //通过adminId获取菜单树
    @GetMapping("/admin/menu/findMenuTreeByAdminId")
    List<MenuVo> findMenuTreeByAdminId(@RequestParam("id") Long id);

    //通过adminId获取权限集合
    @GetMapping("/admin/power/findPowerListByAdminId")
    List<PowerVo> findPowerListByAdminId(@RequestParam("id") Long id);

    //通过adminId获取权限树
    @GetMapping("/admin/power/findPowerTreeByAdminId")
    List<PowerVo> findPowerTreeByAdminId(@RequestParam("id") Long id);

}
