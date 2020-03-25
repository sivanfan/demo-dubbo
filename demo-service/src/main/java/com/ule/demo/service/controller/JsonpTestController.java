package com.ule.demo.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ule.demo.api.req.Request;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanxl
 * @version 1.0
 * @className JsonpTestController
 * @description
 * @date 上午10:38  19-6-28
 **/
@RestController
@RequestMapping("/jsonp")
public class JsonpTestController {

    @RequestMapping(value = "/testJsonp",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject testJsonp(){
        Request request=new Request();
        request.setSystem("JSONP");
        request.setFunctionId("12345");
        String json=JSON.toJSONString(request);
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject;
    }
}
