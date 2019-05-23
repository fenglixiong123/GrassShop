package com.grass.console.common.config;

import com.grass.console.common.interceptor.WebInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author Fenglixiong
 * @Create 2019/5/24 0:15
 * @Description
 **/
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private WebInterceptor webInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor);
        super.addInterceptors(registry);
    }
}
