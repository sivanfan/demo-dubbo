package com.ule.demo.service.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * @Author fanxl
 * @Description 事务控制
 * @Date 16:04 2019/1/30
 **/
@Configuration
public class SysTransactionConfig {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * @Author fanxl
     * @Description 创建事务通知
     * @Date 10:11 2019/3/15
     */
    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor(){
        Properties properties = new Properties();
        properties.setProperty("get*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("query*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");

        TransactionInterceptor tsi = new TransactionInterceptor(dataSourceTransactionManager,properties);
        return tsi;
    }

    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*Service","*HandlerImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }
}
