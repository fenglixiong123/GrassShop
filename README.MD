## Grass-Shop

##项目骨架

grass-api:api接口模块  
grass-common:项目常用模块  
grass-parent:项目父类
    
- grass-web:web服务模块    
- grass-console:后台管理模块  
- grass-admin:管理后台用户模块  

 


##问题解决

1.关于common包无法clean install问题

由于继承的父类中带有springboot的打包插件
```
<plugins>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
</plugins>
```
解决不继承parent

2.关于Feign找不到接口类

需要指定扫描的包
@EnableFeignClients(basePackages = "com.grass.api")  

3.Service id not legal hostname

原因是feign不支持下划线"_"，支持"-",改成xx-sss即可