package com.grass.admin.service.relation.impl;

import com.grass.admin.dao.RoleMenuDao;
import com.grass.admin.model.RoleMenu;
import com.grass.admin.model.RoleMenuExample;
import com.grass.admin.service.relation.RoleMenuService;
import com.grass.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/29 22:48
 * @Description
 **/
@Slf4j
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    /**
     * 通过菜单ID获取角色菜单列表
     * @param roleId
     * @return
     */
    public List<RoleMenu> findListByRoleId(int roleId){
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return roleMenuDao.selectByExample(example);
    }

    /**
     * 通过菜单ID集合获得角色菜单列表
     * @param roleIds
     * @return
     */
    public List<RoleMenu> findListByRoleIds(List<Integer> roleIds){
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdIn(roleIds);
        return roleMenuDao.selectByExample(example);
    }

    /**
     * 通过菜单ID获得菜单列表ID
     * @param roleId
     * @return
     */
    public List<Integer> findMenuIdsByRoleId(int roleId){
        List<RoleMenu> roleMenus = findListByRoleId(roleId);
        List<Integer> menuIds = new ArrayList<>();
        for (RoleMenu roleMenu:roleMenus){
            menuIds.add(roleMenu.getMenuId());
        }
        return menuIds;
    }

    /**
     * 通过菜单IDS获得菜单列表ID
     * @param roleIds
     * @return
     */
    public List<Integer> findMenuIdsByRoleIds(List<Integer> roleIds){
        if(CommonUtils.isEmpty(roleIds)){
            return null;
        }
        List<RoleMenu> roleMenus = findListByRoleIds(roleIds);
        List<Integer> menuIds = new ArrayList<>();
        for (RoleMenu roleMenu:roleMenus){
            menuIds.add(roleMenu.getMenuId());
        }
        return menuIds;
    }

    public Integer add(RoleMenu roleMenu){
        return roleMenuDao.insertSelective(roleMenu);
    }

    public int deleteByRoleId(Integer id){
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(id);
        return roleMenuDao.deleteByExample(example);
    }

    public int deleteByRoleIds(List<Integer> ids){
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdIn(ids);
        return roleMenuDao.deleteByExample(example);
    }

    public int deleteByMenuId(Integer id){
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andMenuIdEqualTo(id);
        return roleMenuDao.deleteByExample(example);
    }

    public int deleteByMenuIds(List<Integer> ids){
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andMenuIdIn(ids);
        return roleMenuDao.deleteByExample(example);
    }
    
}
