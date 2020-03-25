package com.ule.demo.api;

import com.ule.demo.api.req.Request;
import com.ule.demo.api.resp.Response;

/**
 * @Author fanxl
 * @Description 统一处理入口
 * @Date 15:47 2019/1/28
 * @Param
 * @return
 **/
public interface IDealProcessorService {
    /**
     * 统一接口
     * @param request
     * @return
     */
    Response dispatchCommand(Request request);
}
