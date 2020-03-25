package com.ule.demo.manager.sitemesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * @author fanxl
 * @description sitemesh渲染规则配置
 * @date 15:28 2019/1/30
 **/
public class Meshsite3Filter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder
                //拦截规则，/decorator/default 会被转发
                .addDecoratorPath("/*", "/decorator/default")
                //白名单
                .addExcludedPath("/static/**")
                .addExcludedPath("/auth/*")
                .addExcludedPath("/task/*")
                .addExcludedPath("/actuator/**")
                .addExcludedPath("/instances/**")
                //自定义标签
                //.addTagRuleBundle(new MyTagRuleBundle())
                ;
    }
}
