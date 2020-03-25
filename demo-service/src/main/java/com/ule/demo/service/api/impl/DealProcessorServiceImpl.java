package com.ule.demo.service.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.ule.demo.api.IDealProcessorService;
import com.ule.demo.api.req.Request;
import com.ule.demo.api.resp.Response;
import com.ule.demo.common.entity.SystemLog;
import com.ule.demo.common.exceptions.BusinessException;
import com.ule.demo.service.api.autoinstall.MethodAutoInstall;
import com.ule.demo.service.service.systemlog.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @ClassName DealProcessorServiceImpl
 * @Author fanxl
 * @Description request请求统一入口
 * @Date 16:29  2019/1/28
 * @Version 1.0
 */
@Slf4j
@Service(timeout = 3000)
public class DealProcessorServiceImpl implements IDealProcessorService {
    @Autowired
    private SystemLogService systemLogService;
    /**
     * 统一接口
     * @param request
     * @return
     */
    @Override
    public Response dispatchCommand(Request request) {
        Response response;
        long beginTime = System.currentTimeMillis();
        try {
            log.info("Request Sequence:{} , 请求报文：{} " , request.getSequence() , JSON.toJSONString(request));
            try {
                response = MethodAutoInstall.dispatch(request);
            } catch (InvocationTargetException e) {
                Throwable te = e.getTargetException();
                if (te instanceof BusinessException) {
                    log.error("调用功能时业务异常: {}", te.getMessage());
                    response = new Response(((BusinessException) te).getCode(), te.getMessage());
                } else {
                    response = Response.fail("调用功能时系统异常");
                    log.error("调用功能时系统异常:  {}", te);
                }
            }
        } catch (Exception e) {
            response = Response.fail("系统异常");
            log.error("系统异常:{}", e);
        }
        long useTime=System.currentTimeMillis() - beginTime;
        log.info("Request Sequence:{} , 返回报文：{} " , request.getSequence() , JSON.toJSONString(response));
        log.info("Request Sequence:{} , time used:{} (ms) " , request.getSequence() , useTime );
        //记录请求日志
        SystemLog systemLog=new SystemLog();
        systemLog.setSystemCode(request.getSystem());
        systemLog.setFunctionId(request.getFunctionId());
        systemLog.setReqSequence(request.getSequence());
        systemLog.setRequestContent(request.toString());
        systemLog.setResponseContent(response.toString());
        systemLog.setCreateTime(new Date());
        systemLog.setUseTime(useTime);
        systemLogService.save(systemLog);

        return response;
    }
}
