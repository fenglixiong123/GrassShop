package com.grass.api.service.admin;

import com.grass.api.vo.admin.AdminVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 0:02
 * @Description 服务降级，客户端层面，客户端发现服务停止就会采用默认返回值
 **/
@Slf4j
@Component
public class AdminServiceFallBack implements FallbackFactory<IAdminService> {

    public IAdminService create(Throwable throwable) {
        return new IAdminService() {

            public AdminVo getAdmin(Long id) {
                AdminVo adminVo = new AdminVo();
                return adminVo;
            }

            public List<AdminVo> listAdmin() {
                return Arrays.asList(
                        new AdminVo(),
                        new AdminVo(),
                        new AdminVo()
                );
            }
        };
    }

}
