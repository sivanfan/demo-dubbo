package com.ule.demo.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * springboot项目入口
 * @author fanxl
 * @date 10:48 2019/3/15
 * @return
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DemoScheduleApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoScheduleApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoScheduleApplication.class);
    }

}

