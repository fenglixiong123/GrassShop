package com.grass.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Fenglixiong
 * @Create 2019/5/18 23:35
 * @Description
 **/
@Slf4j
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class GrassConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrassConsoleApplication.class, args);
        log.info("GrassConsoleApplication start ...");
    }

}
