package com.grass.admin.service;

import com.grass.api.vo.admin.AdminVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;


public interface AdminService {

    AdminVo get(Long id);

    Long add(AdminVo adminVo);

    int update(AdminVo adminVo);

    int delete(Long id);

    PageResult<AdminVo> list(PageQuery<AdminVo> pageQuery);

    AdminVo getAdminByUsernameAndPassword(String username,String password);


}
