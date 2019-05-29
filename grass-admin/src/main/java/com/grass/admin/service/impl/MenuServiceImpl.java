package com.grass.admin.service.impl;

import com.grass.admin.dao.MenuDao;
import com.grass.admin.model.Menu;
import com.grass.admin.model.MenuExample;
import com.grass.admin.service.AdminRoleService;
import com.grass.admin.service.MenuService;
import com.grass.admin.service.RoleMenuService;
import com.grass.admin.utils.CopyUtil;
import com.grass.api.vo.admin.MenuVo;
import com.grass.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/29 22:47
 * @Description
 **/
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuDao menuDao;

    /**
     * 获取所有菜单列表
     * @return
     */
    public List<MenuVo> findList(){
        List<Menu> menus = menuDao.selectByExample(null);
        return CopyUtil.copyMenuEntity(menus);
    }

    /**
     * 通过adminId获取菜单列表
     * @param adminId
     * @return
     */
    public List<MenuVo> findListByAdminId(Long adminId){
        //1.首先获得用户角色ids
        List<Integer> roleIds = adminRoleService.findRoleIdsByAdminId(adminId);
        //2.获得用户菜单ids
        List<Integer> menuIds = roleMenuService.findMenuIdsByRoleIds(roleIds);
        //3.获取用户菜单
        return findListByMenuIds(menuIds);
    }

    /**
     * 通过roleId获取菜单列表
     * @param roleId
     * @return
     */
    public List<MenuVo> findListByRoleId(Integer roleId){
        List<Integer> menuIds = roleMenuService.findMenuIdsByRoleId(roleId);
        return findListByMenuIds(menuIds);
    }

    /**
     * 通过roleIds获取菜单列表
     * @param roleIds
     * @return
     */
    public List<MenuVo> findListByRoleIds(List<Integer> roleIds){
        List<Integer> menuIds = roleMenuService.findMenuIdsByRoleIds(roleIds);
        return findListByMenuIds(menuIds);
    }

    /**
     * 通过menuIds获取菜单列表
     * @param menuIds
     * @return
     */
    public List<MenuVo> findListByMenuIds(List<Integer> menuIds){
        if(CommonUtils.isEmpty(menuIds)){
            return null;
        }
        MenuExample example = new MenuExample();
        example.createCriteria().andIdIn(menuIds);
        List<Menu> menus = menuDao.selectByExample(example);
        return CopyUtil.copyMenuEntity(menus);
    }
    
}
