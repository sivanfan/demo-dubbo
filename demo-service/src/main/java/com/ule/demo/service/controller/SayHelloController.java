package com.ule.demo.service.controller;

import com.ule.demo.common.entity.CertificateInfo;
import com.ule.demo.common.entity.Test;
import com.ule.demo.common.springside.PageInfo;
import com.ule.demo.service.service.cermanager.CerManagerService;
import com.ule.demo.service.service.test.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestController
 * @Author fanxl
 * @Description 提供给外部系统的接口demo
 * @Date 10:20  2019/2/13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/outapi")
@RefreshScope
public class SayHelloController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    CerManagerService cerManagerService;
    @Autowired
    TestService testService;

    @Value("${user.name}")
    private String name;

    @RequestMapping("/test1")
    @ResponseBody
    public Integer test1() {
        Map map=new HashMap();
        map.put("cerType",3);
        map.put("cerEnv",1);
        List<CertificateInfo> list= cerManagerService.selectSomeResult(map);
        logger.info("***********{}",list.get(0).getBusiName());
        int result=cerManagerService.getRefundAmount(map);
        logger.info("***********{}",result);
        Map resultMap=cerManagerService.getRefundAmountAndPoint(map);
        logger.info("***********{}",resultMap.get("sumCerEnv"));
        logger.info("***********{}",resultMap.get("sumCerType"));
        return result;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Integer test() {
        Test t=new Test();
        t.setUserName("afa");
        testService.save(t);
        return 1;
    }
    @RequestMapping(value = "/test2")
    @ResponseBody
    public PageInfo test2() {
        PageInfo pageInfo=new PageInfo();

        return testService.getPage(pageInfo);
    }

    @RequestMapping(value = "/test3")
    @ResponseBody
    public String test3() {
        return "====测试===="+name;
    }
}
