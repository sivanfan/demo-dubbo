package com.ule.demo.service.config;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.alibaba.druid.util.Utils;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName DruidMonitorConfig
 * @Author fanxl
 * @Description druid连接池监控配置管理
 *              项目启用了数据源监控功能，访问地址:http://localhost:8766/demo-service/druid/index.html
 *              黑名单>白名单，deny优先于allow，如果在deny列表中，就算在allow列表中，也会被拒绝
 * @Date 17:02  2019/3/14
 * @Version 1.0
 **/
@Configuration
public class DruidMonitorConfig {
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //IP白名单
        //servletRegistrationBean.addInitParameter("allow","192.168.116.188,127.0.0.1");
        //IP黑名单
        //servletRegistrationBean.addInitParameter("deny","192.168.116.188");
        //控制台用户
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean(name = "druid-stat-interceptor")
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor dsInterceptor = new DruidStatInterceptor();
        return dsInterceptor;
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("druid-stat-interceptor");
        //根据自己系统的需要设置需要监控的代码层次 --> "*Service", "*ServiceImpl",
        creator.setBeanNames("*Service","*HandlerImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }

    //以下代码只是为了去掉监控平台上自带的阿里云广告，可以忽略
    /**
     * 带有广告的common.js全路径，druid-1.1.10
     */
    private static final String FILE_PATH = "support/http/resources/js/common.js";
    /**
     * 原始脚本，触发构建广告的语句
     */
    private static final String ORIGIN_JS = "this.buildFooter();";
    /**
     * 替换后的脚本
     */
    private static final String NEW_JS = "//this.buildFooter();";
    /**
     * 去除Druid监控页面的广告
     * @param properties DruidStatProperties属性集合
     */
    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnBean(ServletRegistrationBean.class)
    public FilterRegistrationBean<RemoveAdFilter> removeDruidAdFilter(
            DruidStatProperties properties) throws IOException {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        // 获取common.js
        String text = Utils.readFromResource(FILE_PATH);
        // 屏蔽 this.buildFooter(); 不构建广告
        final String newJs = text.replace(ORIGIN_JS, NEW_JS);
        FilterRegistrationBean<RemoveAdFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RemoveAdFilter(newJs));
        registration.addUrlPatterns(commonJsPattern);
        return registration;
    }

    /**
     * 删除druid的广告过滤器
     */
    private class RemoveAdFilter implements Filter {

        private final String newJs;

        public RemoveAdFilter(String newJS) {
            this.newJs = newJS;
        }
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            chain.doFilter(request, response);
            // 重置缓冲区，响应头不会被重置
            response.resetBuffer();
            response.getWriter().write(newJs);
        }
        @Override
        public void destroy() {
        }
    }
}
