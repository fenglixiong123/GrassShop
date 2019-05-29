package com.grass.api.service.admin.fallback;

import com.grass.api.service.admin.IAdminService;
import com.grass.api.vo.admin.AdminVo;
import com.grass.api.vo.admin.MenuVo;
import com.grass.api.vo.admin.PowerVo;
import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:02
 * @Description 服务降级，客户端层面，客户端发现服务停止就会采用默认返回值
 **/
@Slf4j
@Component
public class AdminServiceFallbackFactory implements FallbackFactory<IAdminService> {

    public IAdminService create(Throwable throwable) {
        return new IAdminService() {

            public AdminVo get(Long id) {
                return new AdminVo(-99L,"default");
            }

            public Long add(AdminVo adminVo) {
                return -99L;
            }

            public int update(AdminVo adminVo) {
                return -99;
            }

            public int delete(Long id) {
                return -99;
            }

            public PageResult<AdminVo> list(@RequestBody(required = false) PageQuery<AdminVo> pageQuery) {
                return new PageResult<AdminVo>();
            }

            public AdminVo getAdminByUsernameAndPassword(String username, String password) {
                return new AdminVo(-99L,"default");
            }

            public List<MenuVo> findMenuByAdminId(Long id) {
                return null;
            }

            public List<PowerVo> findPowerByAdminId(Long id) {
                return new ArrayList<PowerVo>();
            }
        };
    }

}
