package com.grass.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Fenglixiong
 * @Create 2019/5/18 16:35
 * @Description
 **/
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

    private static final Logger log = LoggerFactory.getLogger(EurekaServer.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class, args);
        log.info("EurekaServer start ...");
    }
}
