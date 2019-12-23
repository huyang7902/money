package com.huyang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @Author yang.hu
 * @Date 2019/12/23 10:56
 */

@ImportResource(locations = {"classpath:spring/spring-application.xml"})
@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = "com.huyang.dao")
@EnableCaching
@EnableTransactionManagement
public class MoneyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MoneyApplication.class, args);
    }

    /**
     * 使用tomcat容器部署，tomcat容器引导
     * 使用此方式，需要把tomcat-embed-jasper依赖的scop改为provided
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MoneyApplication.class);
    }
}

