package com.grass.admin.utils;

import com.grass.admin.model.Admin;
import com.grass.admin.model.Menu;
import com.grass.admin.model.Power;
import com.grass.admin.model.Role;
import com.grass.api.vo.admin.AdminVo;
import com.grass.api.vo.admin.MenuVo;
import com.grass.api.vo.admin.PowerVo;
import com.grass.api.vo.admin.RoleVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Fenglixiong
 * @Date: 2019/5/28 13:56
 * @Description:
 */
public class CopyUtil {

    public static AdminVo copyAdminEntity(Admin admin){
        AdminVo adminVo = new AdminVo();
        BeanUtils.copyProperties(admin,adminVo);
        return adminVo;
    }

    public static List<AdminVo> copyAdminEntity(List<Admin> admins){
        List<AdminVo> adminVos = new ArrayList<>();
        for (Admin admin : admins){
            AdminVo adminVo = new AdminVo();
            BeanUtils.copyProperties(admin,adminVo);
            adminVos.add(adminVo);
        }
        return adminVos;
    }

    public static RoleVo copyRoleEntity(Role role){
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(role,roleVo);
        return roleVo;
    }

    public static List<RoleVo> copyRoleEntity(List<Role> roles){
        List<RoleVo> roleVos = new ArrayList<>();
        for (Role role : roles){
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role,roleVo);
            roleVos.add(roleVo);
        }
        return roleVos;
    }

    public static PowerVo copyPowerEntity(Power power){
        PowerVo powerVo = new PowerVo();
        BeanUtils.copyProperties(power,powerVo);
        return powerVo;
    }

    public static List<PowerVo> copyPowerEntity(List<Power> powers){
        List<PowerVo> powerVos = new ArrayList<>();
        for (Power power : powers){
            PowerVo powerVo = new PowerVo();
            BeanUtils.copyProperties(power,powerVo);
            powerVos.add(powerVo);
        }
        return powerVos;
    }

    public static MenuVo copyMenuEntity(Menu menu){
        MenuVo menuVo = new MenuVo();
        BeanUtils.copyProperties(menu,menuVo);
        return menuVo;
    }

    public static List<MenuVo> copyMenuEntity(List<Menu> menus){
        List<MenuVo> menuVos = new ArrayList<>();
        for (Menu menu : menus){
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(menu,menuVo);
            menuVos.add(menuVo);
        }
        return menuVos;
    }

}
