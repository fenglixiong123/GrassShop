package com.grass.admin.service.impl;

import com.grass.admin.dao.RoleDao;
import com.grass.admin.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:14
 * @Description
 **/
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;



}
