
## grass-console

1.找不到fallback bean

- 没有加@Component
- console的扫描包没有扫到
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {"com.grass.console", "com.grass.api.service"}) 
        
        