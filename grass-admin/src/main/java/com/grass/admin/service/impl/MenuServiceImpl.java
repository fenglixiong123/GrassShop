package com.grass.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.admin.dao.MenuDao;
import com.grass.admin.model.Menu;
import com.grass.admin.model.MenuExample;
import com.grass.admin.service.AdminRoleService;
import com.grass.admin.service.MenuService;
import com.grass.admin.service.RoleMenuService;
import com.grass.admin.utils.CopyUtil;
import com.grass.api.vo.admin.MenuVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.utils.CommonUtils;
import com.grass.web.exception.element.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public MenuVo get(Integer id) {
        return CopyUtil.copyMenuEntity(menuDao.selectByPrimaryKey(id));
    }

    @Override
    public Integer add(MenuVo menuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo,menu);
        menuDao.insertSelective(menu);
        return menu.getId();
    }

    @Override
    public int update(MenuVo menuVo) {
        if(menuVo.getId()==null){
            throw new ParamException("更新ID为空！");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo,menu);
        return menuDao.updateByPrimaryKeySelective(menu);
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        int res = menuDao.deleteByPrimaryKey(id);
        //删除角色菜单关系
        roleMenuService.deleteByMenuId(id);
        return res;
    }

    @Override
    public PageResult<MenuVo> list(PageQuery<MenuVo> pageQuery) {
        Page<Menu> page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        List<Menu> menuList = queryList(pageQuery);
        PageInfo<Menu> pageInfo =new PageInfo<>(page);
        return new PageResult<>(pageInfo, CopyUtil.copyMenuEntity(menuList));
    }

    @Override
    public List<MenuVo> listAll(PageQuery<MenuVo> pageQuery) {
        return CopyUtil.copyMenuEntity(queryList(pageQuery));
    }

    private List<Menu> queryList(PageQuery<MenuVo> pageQuery){
        MenuExample example = new MenuExample();
        MenuVo queryVo = pageQuery.getEntity();
        if(queryVo!=null){
            MenuExample.Criteria criteria = example.createCriteria();
            if(queryVo.getId()!=null){
                criteria.andIdEqualTo(queryVo.getId());
            }
            if(StringUtils.isNotBlank(queryVo.getTitle())){
                criteria.andTitleLike("%"+queryVo.getTitle()+"%");
            }
        }
        return menuDao.selectByExample(example);
    }

}
