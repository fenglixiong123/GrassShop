package com.grass.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.admin.dao.AdminDao;
import com.grass.admin.model.Admin;
import com.grass.admin.model.AdminExample;
import com.grass.admin.service.AdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public AdminVo getAdmin(Long id) {
        AdminVo adminVo = new AdminVo();
        Admin admin = adminDao.selectByPrimaryKey(id);
        BeanUtils.copyProperties(admin,adminVo);
        return adminVo;
    }

    @Override
    public PageResult<AdminVo> listAdmin(PageQuery<AdminVo> pageQuery) {
        Page<Admin> page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        AdminExample example = new AdminExample();
        List<Admin> adminList =adminDao.selectByExample(example);
        PageInfo<Admin> pageInfo =new PageInfo<>(page);
        List<AdminVo> adminVoList = new ArrayList<>();
        adminList.forEach(admin->{
            AdminVo adminVo = new AdminVo();
            BeanUtils.copyProperties(admin,adminVo);
            adminVoList.add(adminVo);
        });

        return new PageResult<>(pageInfo,adminVoList);
    }
}
