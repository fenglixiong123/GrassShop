package com.grass.admin.service;

import com.grass.api.vo.admin.AdminVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;


public interface AdminService {

    AdminVo getAdmin(Long id);

    PageResult<AdminVo> listAdmin(PageQuery<AdminVo> pageQuery);

}
