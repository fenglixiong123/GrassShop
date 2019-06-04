package com.grass.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.admin.dao.RoleDao;
import com.grass.admin.model.Role;
import com.grass.admin.model.RoleExample;
import com.grass.admin.service.AdminRoleService;
import com.grass.admin.service.RoleService;
import com.grass.admin.utils.CopyUtil;
import com.grass.api.vo.admin.RoleVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.web.exception.element.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int delete(Integer id) {
        return roleDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<RoleVo> list(PageQuery<RoleVo> pageQuery) {
        Page<Role> page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        PageInfo<Role> pageInfo =new PageInfo<>(page);
        return new PageResult<>(pageInfo, listAll(pageQuery));
    }

    @Override
    public List<RoleVo> listAll(PageQuery<RoleVo> pageQuery) {
        RoleExample example = new RoleExample();
        RoleVo queryVo = pageQuery.getEntity();
        if(queryVo!=null){
            RoleExample.Criteria criteria = example.createCriteria();
            if(queryVo.getId()!=null){
                criteria.andIdEqualTo(queryVo.getId());
            }
            if(StringUtils.isNotBlank(queryVo.getTitle())){
                criteria.andTitleLike("%"+queryVo.getTitle()+"%");
            }
        }
        List<Role> roleList = roleDao.selectByExample(example);
        return CopyUtil.copyRoleEntity(roleList);
    }
}
