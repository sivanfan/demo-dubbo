package com.ule.demo.eltjob.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName DruidConnectConfig
 * @Author fanxl
 * @Description Druid连接池配置
 * @Date 10:23  2019/2/26
 * @Version 1.0
 **/
@Component
@Configuration
@PropertySource("classpath:jdbc2.properties")
public class DruidConnectConfig {
    @Value("${jdbc.ule_certificate-master.url}")
    private String url;

    @Value("${jdbc.ule_certificate-master.username}")
    private String username;

    @Value("${jdbc.ule_certificate-master.password}")
    private String password;

    private String driverClassName="com.mysql.jdbc.Driver";

    @Value("${jdbc.ule_certificate-master.initialSize}")
    private int initialSize;

    @Value("${jdbc.ule_certificate-master.minIdle}")
    private int minIdle;

    @Value("${jdbc.ule_certificate-master.maxActive}")
    private int maxActive;

    @Value("${jdbc.ule_certificate-master.maxWait}")
    private int maxWait;

    private int minEvictableIdleTimeMillis=5*60*1000;

    @Value("${jdbc.ule_certificate-master.validationQuery}")
    private String validationQuery;

    private boolean poolPreparedStatements=true;

    private int maxPoolPreparedStatementPerConnectionSize=20;

    private String filters="stat,wall,slf4j";

    @Bean
    @Primary
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("-------->druid configuration initialization filter: "+ e);
        }
        return datasource;
    }

}
