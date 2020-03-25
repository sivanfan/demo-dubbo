package com.ule.demo.manager.springside;

import com.ule.demo.common.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author fanxl
 * @Description 全局异常处理
 * @Date 17:10  2019/1/29
 * @Version 1.0
 **/
@ControllerAdvice
@Slf4j
@Controller
public class GlobalExceptionHandler implements ErrorController {

    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * 错误请求
     */
    @RequestMapping(PATH)
    public ModelAndView badRequest(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        int status = response.getStatus();
        switch (status) {
            case 403:
                modelAndView.setViewName("/error/error");
                break;
            case 404:
                modelAndView.setViewName("/error/500");
                break;
            case 500:
                modelAndView.setViewName("/error/500");
                break;
             default:
                modelAndView.setViewName("/error/500");
                 break;
        }
        modelAndView.addObject("menuid", 1);
        return modelAndView;
    }
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView  errorHandler(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error/500");
        modelAndView.addObject("menuid", 1);
        return modelAndView;
    }

    /**
     * 拦截捕捉自定义异常 返回json格式
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Map myErrorHandler(BusinessException ex) {
        Map map = new HashMap(16);
        map.put("code", ex.getCode());
        map.put("msg", ex.getMessage());
        return map;
    }

}
