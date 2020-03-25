package com.ule.demo.service.common;

import groovy.lang.Script;

/**
 * @author fanxl
 * @version 1.0
 * @className TestScript
 * @description
 * @date 上午9:32  19-4-3
 **/
public class TestScript extends Script {
    @Override
    public Object run() {
        return null;
    }

    public Integer add (Integer first, Integer second) {
        return first + second;
    }
}
