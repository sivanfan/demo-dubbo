package com.ule.demo.eltjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * springboot项目入口
 * @author fanxl
 * @date 10:48 2019/3/15
 * @return
 */
@SpringBootApplication
@ImportResource("classpath:jobs.xml")
public class DemoEltJobApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoEltJobApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoEltJobApplication.class);
    }

}

