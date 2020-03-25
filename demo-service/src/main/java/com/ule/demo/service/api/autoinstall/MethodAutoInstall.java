package com.ule.demo.service.api.autoinstall;

import com.ule.demo.service.api.autoinstall.anno.FunctionId;
import com.ule.demo.service.handler.FunctionService;
import com.ule.demo.service.utils.ClassUtil;
import com.ule.demo.api.req.Request;
import com.ule.demo.api.resp.Response;
import com.ule.demo.common.exceptions.BusinessException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MethondAutoInstall
 * @Author fanxl
 * @Description 把handler包下的方法和functionid存入映射集合 , 处理接收到的request根据functionid进行分发
 * @Date 16:38  2019/1/28
 * @Version 1.0
 **/
@Component
public class MethodAutoInstall implements ApplicationContextAware {
    private final static Map<String, Object[]> METHOD_CACHE_MAP = new ConcurrentHashMap<>();

    @PostConstruct
    public void onApplicationEvent() throws Exception {
        System.out.println("ApplicationRunner,MethodAutoInstall.......");
        METHOD_CACHE_MAP.clear();
        List<String> list = ClassUtil.getClassPathClassNames(ClassUtil.HANDLER_PACKAGE, true);
        for (String className : list) {
            Class<?> clazz = Class.forName(className);
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                FunctionId functionId = method.getAnnotation(FunctionId.class);
                if (functionId != null) {
                    if (METHOD_CACHE_MAP.containsKey(functionId.value())) {
                        throw new BusinessException("------->重复的方法编号: " + functionId.value());
                    }
                    METHOD_CACHE_MAP.put(functionId.value(), new Object[]{applicationContext.getBean(clazz), method});
                }
            }
        }
    }

    /**
     * 分发主入口
     * @param request
     * @return
     * @throws Exception
     */
    public static Response dispatch(Request request) throws Exception {
        String functionId = request.getFunctionId();
        Object[] objs = METHOD_CACHE_MAP.get(functionId);
        int objsLength=2;
        if (null == objs || objs.length != objsLength) {
            throw new BusinessException("------->方法编号：" + functionId + " 不存在");
        }
        FunctionService functionService = (FunctionService)objs[0];
        Method method = (Method)objs[1];
        return (Response)method.invoke(functionService, request);
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
}
