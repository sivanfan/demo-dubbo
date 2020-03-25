package com.ule.demo.manager.springside.decorator;

import  org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Author fanxl
 * @Description simesh渲染默认跳转类
 * @Date 15:17 2019/1/30
 **/
@Controller
@RequestMapping("/decorator")
public class DecoratorController {
    @RequestMapping("default")
    public String defaultDecorator() {
        return "/decorator/default";
    }
}