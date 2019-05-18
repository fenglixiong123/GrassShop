package com.grass.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrassAdminApplication {

    private static final Logger log = LoggerFactory.getLogger(GrassAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GrassAdminApplication.class, args);
        log.info("GrassAdminApplication start ...");
    }

}
