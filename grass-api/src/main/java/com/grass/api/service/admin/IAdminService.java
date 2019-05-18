package com.grass.api.service.admin;

import com.grass.api.vo.admin.AdminVo;
import com.grass.common.constants.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:01
 * @Description
 **/
@FeignClient(value = AppConstant.GRASS_ADMIN)
public interface IAdminService {

    @RequestMapping(value = "/admin/get/{id}",method = RequestMethod.GET)
    AdminVo getAdmin(@PathVariable("id") Long id);

    @RequestMapping(value = "/admin/list",method = RequestMethod.GET)
    List<AdminVo> listAdmin();

}
