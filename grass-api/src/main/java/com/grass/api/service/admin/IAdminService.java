package com.grass.api.service.admin;

import com.grass.api.service.admin.fallback.AdminServiceFallbackFactory;
import com.grass.api.vo.admin.*;
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
    AdminVo getAdmin(@PathVariable("id") Long id);

    //添加用户
    @PostMapping("/admin")
    Long addAdmin(@RequestBody AdminVo adminVo);

    //修改用户
    @PutMapping("/admin")
    int updateAdmin(@RequestBody AdminVo adminVo);

    //删除用户
    @DeleteMapping("/admin/{id}")
    int deleteAdmin(@PathVariable("id") Long id);

    //用户列表带分页
    @PostMapping("/admin/list")
    PageResult<AdminVo> listPageAdmin(@RequestBody(required = false) PageQuery<AdminVo> pageQuery);

    //根据用户名密码查询用户
    @PostMapping("/admin/getAdminByUsernameAndPassword")
    AdminVo getAdminByUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password);

    //根据用户ID查询拥有的角色
    @GetMapping("/admin/findPossessRoleByAdminId/{id}")
    PossessRole findPossessRoleByAdminId(@PathVariable("id") Long id);

    //分配角色给用户
    @PostMapping("/admin/assignRoleToAdmin")
    void assignRoleToAdmin(@RequestParam("roleIds") List<Integer> roleIds, @RequestParam("adminId") Long adminId);

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

    @GetMapping("/admin/menu/{id}")
    MenuVo getMenu(@PathVariable("id") Integer id);

    @PostMapping("/admin/menu")
    Integer addMenu(@RequestBody MenuVo menuVo);

    @PutMapping("/admin/menu")
    int updateMenu(@RequestBody MenuVo menuVo);

    @DeleteMapping("/admin/menu/{id}")
    int deleteMenu(@PathVariable("id") Integer id);

    @PostMapping("/admin/menu/list")
    PageResult<MenuVo> listPageMenu(@RequestBody(required = false) PageQuery<MenuVo> pageQuery);

    //通过adminId获取菜单集合
    @GetMapping("/admin/menu/findMenuListByAdminId")
    List<MenuVo> findMenuListByAdminId(@RequestParam("id") Long id);

    //通过adminId获取菜单树
    @GetMapping("/admin/menu/findMenuTreeByAdminId")
    List<MenuVo> findMenuTreeByAdminId(@RequestParam("id") Long id);

    //权限相关API---------------------------------------------------------------------------------------

    @GetMapping("/admin/power/{id}")
    PowerVo getPower(@PathVariable("id") Integer id);

    @PostMapping("/admin/power")
    Integer addPower(@RequestBody PowerVo powerVo);

    @PutMapping("/admin/power")
    int updatePower(@RequestBody PowerVo powerVo);

    @DeleteMapping("/admin/power/{id}")
    int deletePower(@PathVariable("id") Integer id);

    @PostMapping("/admin/power/list")
    PageResult<PowerVo> listPagePower(@RequestBody(required = false) PageQuery<PowerVo> pageQuery);

    //通过adminId获取权限集合
    @GetMapping("/admin/power/findPowerListByAdminId")
    List<PowerVo> findPowerListByAdminId(@RequestParam("id") Long id);

    //通过adminId获取权限树
    @GetMapping("/admin/power/findPowerTreeByAdminId")
    List<PowerVo> findPowerTreeByAdminId(@RequestParam("id") Long id);

}
