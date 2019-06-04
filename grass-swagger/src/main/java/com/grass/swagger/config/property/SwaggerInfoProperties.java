package com.grass.swagger.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Fenglixiong
 * @Create 2018.11.07 17:36
 * @Description Swagger基本信息的一些配置
 **/
@Data
@ConfigurationProperties(prefix = SwaggerInfoProperties.PREFIX)
public class SwaggerInfoProperties {

    public static final String PREFIX = "grass.swagger";

    private String groupName = "中国稀有物种网";

    private String basePackage = "";

    private String author = "冯立雄";

    private String homepage = "https://github.com/fenglixiong123";

    private String email = "fenglixiong123@163.com";

    private String title = "后台管理服务API";

    private String description = "swagger ui for China website";

    private String version = "1.0.0";

    private String license = "None";

    private String licenseUrl = "www.apache.com";

    private String termsOfServiceUrl = "https://www.china.com.cn";

}
