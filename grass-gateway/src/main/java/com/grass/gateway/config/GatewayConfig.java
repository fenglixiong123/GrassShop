package com.grass.gateway.config;

import com.grass.common.constants.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 11:35
 * @Description
 **/
@Slf4j
@Configuration
public class GatewayConfig {

    private String console_url = "lb://"+AppConstant.GRASS_CONSOLE;
    private String admin_url = "lb://"+AppConstant.GRASS_ADMIN;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        log.info("加载路由：{}",console_url);
        log.info("加载路由：{}",admin_url);
        return builder.routes()
                .route(AppConstant.GRASS_ADMIN,r->r.path("/admin/**")
                        .filters(f->f.hystrix(config->config
                                                .setName(AppConstant.GRASS_ADMIN)
                                                .setFallbackUri("forward:/admin/fallback")))
                        .uri(admin_url))
                .route(AppConstant.GRASS_CONSOLE,r->r.path("/console/**")
                        .uri(console_url))
                .build();
    }

}
