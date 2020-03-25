package com.ule.demo.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.ule.demo.api.IDealProcessorService;
import com.ule.demo.api.req.Request;
import com.ule.demo.api.resp.Response;
import com.ule.demo.common.constants.Constants;
import com.ule.demo.common.constants.FunctionNo;
import com.ule.demo.common.entity.CertificateInfo;
import com.ule.demo.common.springside.PageInfo;
import com.ule.demo.manager.config.Config;
import com.ule.demo.manager.utils.ConvertUtil;
import com.ule.demo.manager.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Author fanxl
 * @Description: 证书管理模块 控制类
 * @Create: 19-1-10 下午5:46
 */
@Controller
@RequestMapping(value = "/cermanager")
@Slf4j
public class CerManagerController {
    @Autowired
    private Config config;
    @Reference
    private IDealProcessorService dealProcessorService;
    /**
     * @description 查询列表
     * @author fanxunli
     * @date
     * @param model
     * @param request
     * @return java.lang.String
     */
    @RequestMapping({"/query", "/", ""})
    public String queryCerInfo(Model model, HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "rows", defaultValue = "10") int rows,
                               @RequestParam(value = "sort", defaultValue = "") String sort,
                               @RequestParam(value = "order", defaultValue = "desc") String order) {
        String searchval = request.getParameter("searchval");
        String cersendtype = request.getParameter("cersendtype") == null ? "1" : request.getParameter("cersendtype");

        CertificateInfo certificateInfo = new CertificateInfo();
        certificateInfo.setSysName(searchval);
        certificateInfo.setCerSendType(Integer.parseInt(cersendtype));
        certificateInfo.setSort(formateSort("expirationDate"));
        certificateInfo.setOrder(order);
        PageInfo body = new PageInfo();
        body.setPageNo(page);
        body.setPageSize(rows);
        body.setQueryParam(certificateInfo);
        Request httpRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_100004, body);

        Response rep = dealProcessorService.dispatchCommand(httpRequest);
        log.info("============"+JSON.toJSON(rep));
        PageInfo pageInfo = (PageInfo) rep.getData();
        List<CertificateInfo> infolist = pageInfo.getResults();

        model.addAttribute("searchval", searchval);
        model.addAttribute("infolist", infolist);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageInfo.getTotalPage());
        model.addAttribute("cersendtype", cersendtype);
        if ("1".equals(cersendtype)) {
            model.addAttribute("menuid", 1);
        } else {
            model.addAttribute("menuid", 2);
        }
        return "/certificate/list";
    }

    private String formateSort(String sort) {
        if ("expirationDate".equals(sort)){
            return "expiration_Date";
        }
        return sort;
    }

    /**
     * @description 证书生成
     * @author fanxunli
     * @date
     * @param model
     * @param request
     * @return java.lang.String
     */
    @RequestMapping(value = "/createCer")
    public String createCer(Model model, HttpServletRequest request) {
        model.addAttribute("menuid", 3);
        return "/certificate/add";
    }

    /**
     * @description  证书生成保存
     * @author fanxunli
     * @date
     * @param model
     * @param request
     * @return java.lang.String
     */
    @RequestMapping(value = "/saveCer")
    public String saveCer(Model model, HttpServletRequest request) {
        String busiName = request.getParameter("busiName");
        String sysName = request.getParameter("sysName");
        String downPassword = request.getParameter("downPassword");
        String cerPassword = request.getParameter("cerPassword");
        String email = request.getParameter("email");
        String cerEnv = request.getParameter("cer_env");

        CertificateInfo certificateInfo = new CertificateInfo();
        certificateInfo.setBusiName(busiName);
        certificateInfo.setSysName(sysName);
        certificateInfo.setCerType(3);
        certificateInfo.setCerSendType(1);
        certificateInfo.setCerEnv(Integer.parseInt(cerEnv));

        Request httpQueryRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_100003, certificateInfo);
        Response rep = dealProcessorService.dispatchCommand(httpQueryRequest);
        List<CertificateInfo> list = (List<CertificateInfo>) rep.getData();

        if (!CollectionUtils.isEmpty(list)) {
            model.addAttribute("errormsg", "该系统的证书已经存在");
            model.addAttribute("menuid", 1);
            return "/error/error";
        }
        CertificateInfo info = new CertificateInfo();
        info.setBusiName(busiName);
        info.setSysName(sysName);
        info.setCerSendType(1);
        info.setChannelCode("ule");
        info.setDownPassword(downPassword);
        info.setCerPassword(cerPassword);
        info.setEmail(email);
        info.setExpirationDate(DateUtil.plusDay2(config.getExpirationDay()));
        info.setCerEnv(Integer.parseInt(cerEnv));
        info.setCerType(3);
        info.setCerContent("22222222222222222222");
        info.setCerName(sysName+".pfx");
        info.setCreateTime(new Date());
        //把请求内容传给cer-service系统
        Request httpCreRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_100001, info);
        Response repCre = dealProcessorService.dispatchCommand(httpCreRequest);
        if (Constants.CODE_SUCCESS.equals(repCre.getCode())) {
            model.addAttribute("menuid", 1);
            return "redirect:/cermanager/query?cersendtype=1";
        } else {
            model.addAttribute("menuid", 1);
            model.addAttribute("errormsg", "系统错误，请联系管理员！");
            return "/error/error";
        }
    }

    /**
     * @description  证书上传
     * @author fanxunli
     * @date
     * @param model
     * @param request
     * @return java.lang.String
     */
    @RequestMapping(value = "/uploadCer")
    public String uploadCer(Model model, HttpServletRequest request) {
        model.addAttribute("menuid", 4);
        return "/certificate/upload";
    }

    /**
     * @description  证书上传保存
     * @author fanxunli
     * @date
     * @param model
     * @param request
     * @param cerfile
     * @return java.lang.String
     */
    @RequestMapping(value = "/saveUploadCer")
    public String saveUploadCer(Model model, HttpServletRequest request, @RequestParam("cerfile") MultipartFile cerfile) {
        String busiName = request.getParameter("busiName");
        String sysName = request.getParameter("sysName");
        String downPassword = request.getParameter("downPassword");
        String channelCode = request.getParameter("channelCode");
        String cerEnv = request.getParameter("cer_env");
        String email = request.getParameter("email");
        String cerOutType = request.getParameter("certype");

        CertificateInfo certificateInfo = new CertificateInfo();
        certificateInfo.setBusiName(busiName);
        certificateInfo.setSysName(sysName);
        certificateInfo.setCerType(1);
        certificateInfo.setCerSendType(2);
        certificateInfo.setCerEnv(Integer.parseInt(cerEnv));
        certificateInfo.setChannelCode(channelCode);

        Request httpQueryRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_100003, certificateInfo);
        Response rep = dealProcessorService.dispatchCommand(httpQueryRequest);
        List<CertificateInfo> list = (List<CertificateInfo>) rep.getData();
        if (!CollectionUtils.isEmpty(list)) {
            model.addAttribute("errormsg", "该系统的证书已经存在");
            model.addAttribute("menuid", 1);
            return "/error/error";
        }

        String cercontent = "";
        if ("1".equals(cerOutType)) {
            log.info("fileName：{}", cerfile.getOriginalFilename());
            InputStream is = null;
            try {
                is = cerfile.getInputStream();
                //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                // 生成字符串
                cercontent = ConvertUtil.byte2hex(bytes);
            } catch (Exception e) {
                log.error("======CerManagerController======", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("======CerManagerController======", e);
                }
            }
        }
        if ("2".equals(cerOutType)) {
            String cerString = request.getParameter("cerString");
            cercontent = ConvertUtil.byte2hex(cerString.getBytes());
        }

        CertificateInfo info = new CertificateInfo();
        info.setBusiName(busiName);
        info.setSysName(sysName);
        info.setCerSendType(2);
        info.setCerType(1);
        info.setChannelCode(channelCode);
        info.setDownPassword(downPassword);
        info.setCerPassword("");
        info.setEmail(email);
        info.setCerContent(cercontent);
        info.setCerOutType(new Integer(cerOutType));
        info.setCerOutFilename(cerfile.getOriginalFilename());
        info.setCerEnv(Integer.parseInt(cerEnv));
        info.setCerName(sysName + ".pfx");
        log.info(JSON.toJSONString(info));

        //把请求内容传给cer-service系统
        Request httpCreRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_100001, info);
        Response repCre = dealProcessorService.dispatchCommand(httpCreRequest);
        if (Constants.CODE_SUCCESS.equals(repCre.getCode())) {
            model.addAttribute("menuid", 1);
            return "redirect:/cermanager/query?cersendtype=2";
        } else {
            model.addAttribute("menuid", 1);
            model.addAttribute("errormsg", "系统错误，请联系管理员！");
            return "/error/error";
        }
    }

    /**
     * @description  证书删除
     * @author fanxunli
     * @date
     * @return java.lang.String
     */
    @RequestMapping(value = "/delCer")
    public String delCer(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        String cersendtype = request.getParameter("cersendtype");
        //把请求内容传给cer-service系统
        Request delRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_100005, Long.parseLong(id));
        Response repCre = dealProcessorService.dispatchCommand(delRequest);
        if (Constants.CODE_SUCCESS.equals(repCre.getCode())) {
            model.addAttribute("menuid", 1);
            return "redirect:/cermanager/query?cersendtype=" + cersendtype;
        } else {
            model.addAttribute("menuid", 1);
            model.addAttribute("errormsg", "系统错误，请联系管理员！");
            return "/error/error";
        }
    }

}

