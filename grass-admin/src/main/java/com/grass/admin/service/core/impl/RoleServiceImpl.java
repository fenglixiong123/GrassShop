package com.grass.admin.service.core.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.admin.dao.RoleDao;
import com.grass.admin.model.Role;
import com.grass.admin.model.RoleExample;
import com.grass.admin.model.RoleMenu;
import com.grass.admin.model.RolePower;
import com.grass.admin.service.core.MenuService;
import com.grass.admin.service.core.PowerService;
import com.grass.admin.service.relation.AdminRoleService;
import com.grass.admin.service.relation.RoleMenuService;
import com.grass.admin.service.relation.RolePowerService;
import com.grass.admin.service.core.RoleService;
import com.grass.admin.utils.AssignUtil;
import com.grass.admin.utils.ConvertTreeUtil;
import com.grass.admin.utils.CopyUtil;
import com.grass.admin.vo.AddDeleteVo;
import com.grass.api.vo.admin.*;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:14
 * @Description
 **/
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RolePowerService rolePowerService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private PowerService powerService;
    @Autowired
    private RoleDao roleDao;

    /**
     * 根据adminId获取角色列表
     * @param adminId
     * @return
     */
    public List<RoleVo> findListByAdminId(Long adminId){
        List<Integer> roleIds = adminRoleService.findRoleIdsByAdminId(adminId);
        RoleExample example = new RoleExample();
        example.createCriteria().andIdIn(roleIds);
        return CopyUtil.copyRoleEntity(roleDao.selectByExample(example));
    }

    @Override
    public RoleVo get(Integer id) {
        return CopyUtil.copyRoleEntity(roleDao.selectByPrimaryKey(id));
    }

    @Override
    public Integer add(RoleVo roleVo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVo,role);
        roleDao.insertSelective(role);
        return role.getId();
    }

    @Override
    public int update(RoleVo roleVo) {
        if(roleVo.getId()==null){
            throw new ParamException("更新ID为空！");
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleVo,role);
        return roleDao.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        int res = roleDao.deleteByPrimaryKey(id);
        //删除用户角色关系
        adminRoleService.deleteByRoleId(id);
        //删除角色菜单关系
        roleMenuService.deleteByRoleId(id);
        //删除角色权限关系
        rolePowerService.deleteByRoleId(id);
        return res;
    }

    @Override
    @Transactional
    public int deleteRoles(List<Integer> ids){
        RoleExample example = new RoleExample();
        example.createCriteria().andIdIn(ids);
        int res = roleDao.deleteByExample(example);
        //删除用户角色关系
        adminRoleService.deleteByRoleIds(ids);
        //删除角色菜单关系
        roleMenuService.deleteByRoleIds(ids);
        //删除角色权限关系
        rolePowerService.deleteByRoleIds(ids);
        return res;
    }

    @Override
    public PageResult<RoleVo> list(PageQuery<RoleVo> pageQuery) {
        Page<Role> page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        List<Role> roleList = queryList(pageQuery);
        PageInfo<Role> pageInfo =new PageInfo<>(page);
        return new PageResult<>(pageInfo, CopyUtil.copyRoleEntity(roleList));
    }

    @Override
    public List<RoleVo> listAll(PageQuery<RoleVo> pageQuery) {
        return CopyUtil.copyRoleEntity(queryList(pageQuery));
    }

    private List<Role> queryList(PageQuery<RoleVo> pageQuery){
        RoleExample example = new RoleExample();
        if(pageQuery!=null) {
            RoleVo queryVo = pageQuery.getEntity();
            if (queryVo != null) {
                RoleExample.Criteria criteria = example.createCriteria();
                if (queryVo.getId() != null) {
                    criteria.andIdEqualTo(queryVo.getId());
                }
                if (StringUtils.isNotBlank(queryVo.getTitle())) {
                    criteria.andTitleLike("%" + queryVo.getTitle() + "%");
                }
            }
        }
        return roleDao.selectByExample(example);
    }

    @Override
    public PossessMenu findPossessMenuByRoleId(Integer roleId) {
        List<MenuVo> allMenus = ConvertTreeUtil.listToTreeMenu(menuService.listAll(null));
        List<Integer> hasMenus = null;
        if(allMenus!=null) {
            hasMenus = roleMenuService.findMenuIdsByRoleId(roleId);
        }
        return new PossessMenu(allMenus,hasMenus);
    }

    @Override
    public PossessPower findPossessPowerByRoleId(Integer roleId) {
        List<PowerVo> allPowers = ConvertTreeUtil.listToTreePower(powerService.listAll(null));
        List<Integer> hasPowers = null;
        if(allPowers!=null){
            hasPowers = rolePowerService.findPowerIdsByRoleId(roleId);
        }
        return new PossessPower(allPowers,hasPowers);
    }

    @Override
    public void assignMenuToRole(List<Integer> menuIds, Integer roleId) {
        log.info("准备开始给角色【{}】分配菜单{}...",roleId,menuIds);
        //1.首先拿到已拥有的菜单
        List<Integer> hasMenuIds = roleMenuService.findMenuIdsByRoleId(roleId);
        log.info("查询到已经拥有的菜单:{}",hasMenuIds);
        //2.分离需要新增和需要删除的
        AddDeleteVo resolve = AssignUtil.resolve(hasMenuIds, menuIds);
        List<Integer> needAdd = resolve.getNeedAdd();
        List<Integer> needDelete = resolve.getNeedDelete();
        log.info("需要删除的菜单:{},需要新增的菜单:{}",needDelete,needAdd);
        //3.删除已有的菜单关系
        if(CommonUtils.isNotEmpty(needDelete)) {
            log.info("执行删除菜单关系...{}",needDelete);
            roleMenuService.deleteByRoleAndMenus(roleId,needDelete);
        }
        //4.新增所需的菜单关系
        if(CommonUtils.isNotEmpty(needAdd)) {
            log.info("执行新增菜单关系...{}",needAdd);
            needAdd.forEach(menuId->roleMenuService.add(new RoleMenu(roleId,menuId)));
        }
        log.info("已经成功完成对角色菜单分配！");
    }

    @Override
    public void assignPowerToRole(List<Integer> powerIds, Integer roleId) {
        log.info("准备开始给角色【{}】分配权限{}...",roleId,powerIds);
        //1.首先拿到已拥有的权限
        List<Integer> hasPowerIds = rolePowerService.findPowerIdsByRoleId(roleId);
        log.info("查询到已经拥有的权限:{}",hasPowerIds);
        //2.分离需要新增和需要删除的
        AddDeleteVo resolve = AssignUtil.resolve(hasPowerIds, powerIds);
        List<Integer> needAdd = resolve.getNeedAdd();
        List<Integer> needDelete = resolve.getNeedDelete();
        log.info("需要删除的权限:{},需要新增的权限:{}",needDelete,needAdd);
        //3.删除已有的权限关系
        if(CommonUtils.isNotEmpty(needDelete)) {
            log.info("执行删除权限关系...{}",needDelete);
            rolePowerService.deleteByRoleAndPowers(roleId,needDelete);
        }
        //4.新增所需的权限关系
        if(CommonUtils.isNotEmpty(needAdd)) {
            log.info("执行新增权限关系...{}",needAdd);
            needAdd.forEach(powerId->rolePowerService.add(new RolePower(roleId,powerId)));
        }
        log.info("已经成功完成对角色权限分配！");
    }

}
