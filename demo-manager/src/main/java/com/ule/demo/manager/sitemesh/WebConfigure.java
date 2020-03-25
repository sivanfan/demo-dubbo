package com.ule.demo.manager.sitemesh;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *	页面渲染filter
 * @author fanxl
 * @date 10:56 2019/3/15
 * @return
 */
@Configuration
public class WebConfigure {
    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        Meshsite3Filter siteMeshFilter = new Meshsite3Filter();
        filter.setFilter(siteMeshFilter);
        return filter;
    }
}