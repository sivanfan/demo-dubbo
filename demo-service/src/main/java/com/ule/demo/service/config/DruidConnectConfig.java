package com.ule.demo.service.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

    //private String driverClassName="com.mysql.jdbc.Driver";
    private String driverClassName="oracle.jdbc.driver.OracleDriver";

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

        // HikariCP
       /* HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url); //数据源
        config.setUsername(username); //用户名
        config.setPassword(password); //密码
        config.setMinimumIdle(minIdle);
        config.setMaximumPoolSize(maxActive);

        config.addDataSourceProperty("cachePrepStmts", "true"); //是否自定义配置，为true时下面两个参数才生效
        config.addDataSourceProperty("prepStmtCacheSize", "250"); //连接池大小默认25，官方推荐250-500
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //单条语句最大长度默认256，官方推荐2048
        config.addDataSourceProperty("useServerPrepStmts", "true"); //新版本MySQL支持服务器端准备，开启能够得到显著性能提升
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("useLocalTransactionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        HikariDataSource datasource = new HikariDataSource(config);
        return datasource;*/

        return datasource;
    }

}
