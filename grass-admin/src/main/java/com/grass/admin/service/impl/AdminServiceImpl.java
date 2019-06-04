package com.grass.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.admin.dao.AdminDao;
import com.grass.admin.model.Admin;
import com.grass.admin.model.AdminExample;
import com.grass.admin.service.AdminService;
import com.grass.admin.utils.CopyUtil;
import com.grass.api.vo.admin.AdminVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import com.grass.common.utils.CommonUtils;
import com.grass.web.exception.element.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int delete(Long id) {
        return adminDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<AdminVo> list(PageQuery<AdminVo> pageQuery) {
        Page<Admin> page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        PageInfo<Admin> pageInfo =new PageInfo<>(page);
        return new PageResult<>(pageInfo, listAll(pageQuery));
    }

    @Override
    public List<AdminVo> listAll(PageQuery<AdminVo> pageQuery) {
        AdminExample example = new AdminExample();
        AdminVo queryVo = pageQuery.getEntity();
        if(queryVo!=null){
            AdminExample.Criteria criteria = example.createCriteria();
            if(StringUtils.isNotBlank(queryVo.getUsername())){
                criteria.andUsernameLike("%"+queryVo.getUsername()+"%");
            }
            if(StringUtils.isNotBlank(queryVo.getNickname())){
                criteria.andNicknameLike("%"+queryVo.getNickname()+"%");
            }
            if(queryVo.getSex()!=null){
                criteria.andSexEqualTo(queryVo.getSex());
            }
        }
        List<Admin> adminList =adminDao.selectByExample(example);
        return CopyUtil.copyAdminEntity(adminList);
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
}
