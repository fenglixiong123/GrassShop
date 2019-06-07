package com.grass.admin.service.core.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.admin.dao.AdminDao;
import com.grass.admin.model.Admin;
import com.grass.admin.model.AdminExample;
import com.grass.admin.model.AdminRole;
import com.grass.admin.service.relation.AdminRoleService;
import com.grass.admin.service.core.AdminService;
import com.grass.admin.service.core.RoleService;
import com.grass.admin.utils.AssignUtil;
import com.grass.admin.utils.CopyUtil;
import com.grass.admin.vo.AddDeleteVo;
import com.grass.api.vo.admin.AdminVo;
import com.grass.api.vo.admin.PossessRole;
import com.grass.api.vo.admin.RoleVo;
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
import java.util.Date;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:49
 * @Description
 **/
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminRoleService adminRoleService;

    @Override
    public AdminVo get(Long id) {
        return CopyUtil.copyAdminEntity(adminDao.selectByPrimaryKey(id));
    }

    @Override
    public Long add(AdminVo adminVo) {
        Admin admin = new Admin();
        admin.setPassword("123456");
        BeanUtils.copyProperties(adminVo,admin);
        adminDao.insertSelective(admin);
        return admin.getId();
    }

    @Override
    public int update(AdminVo adminVo) {
        if(adminVo.getId()==null){
            throw new ParamException("更新ID为空！");
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminVo,admin);
        admin.setUpdateTime(new Date());
        return adminDao.updateByPrimaryKeySelective(admin);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        int res = adminDao.deleteByPrimaryKey(id);
        //删除用户角色关系
        adminRoleService.deleteByAdminId(id);
        return res;
    }

    @Override
    public PageResult<AdminVo> list(PageQuery<AdminVo> pageQuery) {
        Page<Admin> page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        List<Admin> adminList = queryList(pageQuery);
        PageInfo<Admin> pageInfo =new PageInfo<>(page);
        return new PageResult<>(pageInfo, CopyUtil.copyAdminEntity(adminList));
    }

    @Override
    public List<AdminVo> listAll(PageQuery<AdminVo> pageQuery) {

        return CopyUtil.copyAdminEntity(queryList(pageQuery));
    }

    @Override
    public AdminVo getAdminByUsernameAndPassword(String username, String password) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username)
                .andPasswordEqualTo(password);
        List<Admin> adminList = adminDao.selectByExample(example);
        if(CommonUtils.isNotEmpty(adminList)){
            return CopyUtil.copyAdminEntity(adminList.get(0));
        }
        return null;
    }

    private List<Admin> queryList(PageQuery<AdminVo> pageQuery){
        AdminExample example = new AdminExample();
        if(pageQuery!=null) {
            AdminVo queryVo = pageQuery.getEntity();
            if (queryVo != null) {
                AdminExample.Criteria criteria = example.createCriteria();
                if (StringUtils.isNotBlank(queryVo.getUsername())) {
                    criteria.andUsernameLike("%" + queryVo.getUsername() + "%");
                }
                if (StringUtils.isNotBlank(queryVo.getNickname())) {
                    criteria.andNicknameLike("%" + queryVo.getNickname() + "%");
                }
                if (queryVo.getSex() != null) {
                    criteria.andSexEqualTo(queryVo.getSex());
                }
            }
        }
        return adminDao.selectByExample(example);
    }

    @Override
    public PossessRole findPossessRoleByAdminId(Long adminId){
        List<RoleVo> allRoles = roleService.listAll(null);
        List<Integer> hasRoles = null;
        if(allRoles!=null) {
            hasRoles = adminRoleService.findRoleIdsByAdminId(adminId);
        }
        return new PossessRole(allRoles,hasRoles);
    }

    /**
     * 分配角色给某个用户，涉及到有的角色删除有的角色增加
     * @param roleIds
     * @param adminId
     */
    @Transactional
    public void assignRoleToAdmin(List<Integer> roleIds, Long adminId){
        log.info("准备开始给用户【{}】分配角色{}...",adminId,roleIds);
        //1.首先拿到已拥有的角色
        List<Integer> hasRoleIds = adminRoleService.findRoleIdsByAdminId(adminId);
        log.info("查询到已经拥有的角色:{}",hasRoleIds);
        //2.分离需要新增和需要删除的
        AddDeleteVo resolve = AssignUtil.resolve(hasRoleIds, roleIds);
        List<Integer> needAdd = resolve.getNeedAdd();
        List<Integer> needDelete = resolve.getNeedDelete();
        log.info("需要删除的角色:{},需要新增的角色:{}",needDelete,needAdd);
        //3.删除已有的角色关系
        if(CommonUtils.isNotEmpty(needDelete)) {
            log.info("执行删除角色关系...{}",needDelete);
            adminRoleService.deleteByAdminAndRoles(adminId,needDelete);
        }
        //4.新增所需的角色关系
        if(CommonUtils.isNotEmpty(needAdd)) {
            log.info("执行新增角色关系...{}",needAdd);
            needAdd.forEach(roleId->adminRoleService.add(new AdminRole(adminId,roleId)));
        }
        log.info("已经成功完成对用户角色分配！");
    }

}
