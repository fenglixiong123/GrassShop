package com.grass.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Fenglixiong
 * @Create 2019/5/19 11:36
 * @Description
 **/
@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class GrassGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrassGatewayApplication.class,args);
        log.info("GrassGatewayApplication start=========>");
    }

}
