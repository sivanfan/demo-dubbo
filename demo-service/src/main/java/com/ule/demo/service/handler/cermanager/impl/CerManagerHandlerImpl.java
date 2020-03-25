package com.ule.demo.service.handler.cermanager.impl;

import com.ule.demo.api.req.Request;
import com.ule.demo.api.resp.Response;
import com.ule.demo.common.entity.CertificateInfo;
import com.ule.demo.common.springside.PageInfo;
import com.ule.demo.service.api.autoinstall.anno.FunctionId;
import com.ule.demo.service.handler.FunctionService;
import com.ule.demo.service.handler.cermanager.ICerManagerHandler;
import com.ule.demo.service.service.cermanager.CerManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CerManagerHandlerImpl
 * @Author fanxl
 * @Description 证书管理方法类
 * @Date 17:04  2019/1/28
 * @Version 1.0
 */
@Slf4j
@Service
public class CerManagerHandlerImpl implements ICerManagerHandler {

    @Autowired
    private CerManagerService cerManagerService;

    @FunctionId(value = "100001", desc = "证书模块保存")
    public Response insert(Request request) {
        CertificateInfo entity=(CertificateInfo)request.getBody();
        cerManagerService.save(entity);
        return Response.success();
    }

    @FunctionId(value = "100002", desc = "证书模块更新")
    public Response update(Request request) {
        CertificateInfo entity=(CertificateInfo)request.getBody();
        boolean bResult = cerManagerService.update(entity) != null ? true : false;
        return bResult ? Response.success() : Response.fail("更新失败");
    }

    @FunctionId(value = "100003", desc = "证书模块根据条件查询集合")
    public Response getList(Request request) {
        CertificateInfo entity=(CertificateInfo)request.getBody();
        List<CertificateInfo> list= cerManagerService.queryList(entity);
        return Response.success(list);
    }

    @FunctionId(value = "100004", desc = "证书模块分页查询")
    public Response getPage(Request request) {
        PageInfo pageInfo = (PageInfo)request.getBody();
        pageInfo = cerManagerService.getPage(pageInfo);
        return Response.success(pageInfo);
    }

    @FunctionId(value = "100005", desc = "证书模块删除对象")
    public Response delete(Request request) {
        Long id=(Long)request.getBody();
        int result=cerManagerService.delete(id);
        return result>0?Response.success():Response.fail("删除失败");
    }
}
