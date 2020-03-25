package com.ule.demo.service.handler.cermanager;

import com.ule.demo.api.req.Request;
import com.ule.demo.api.resp.Response;
import com.ule.demo.service.handler.FunctionService;

/**
 * @ClassName ICerManagerHandler
 * @Author fanxl
 * @Description
 * @Date 17:03  2019/1/28
 * @Version 1.0
 **/
public interface ICerManagerHandler extends FunctionService {
    Response insert(Request request);

    Response update(Request request);

    Response getList(Request request);

    Response getPage(Request request);

    Response delete(Request request);
}
