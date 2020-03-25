package com.ule.demo.manager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 *	配置类
 * @author fanxl
 * @date 10:48 2019/3/15
 * @return
 */
@Component
@PropertySource("classpath:properties/cermanager.properties")
@ConfigurationProperties(prefix = "demo")
public class Config {
    int expirationDay;

    public int getExpirationDay() {
        return expirationDay;
    }

    public void setExpirationDay(int expirationDay) {
        this.expirationDay = expirationDay;
    }

}
