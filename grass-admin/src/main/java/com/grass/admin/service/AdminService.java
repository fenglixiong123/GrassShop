package com.grass.admin.service;

import com.grass.api.vo.admin.AdminVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;


public interface AdminService extends BaseService<AdminVo,Long>{

    AdminVo getAdminByUsernameAndPassword(String username,String password);


}
