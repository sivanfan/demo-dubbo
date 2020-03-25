package com.ule.demo.service.api.autoinstall.anno;

import java.lang.annotation.*;

/**
 * @Author fanxl
 * @Description 该注解被使用在一级功能service 中的功能处理方法上
 * @Date 16:46 2019/1/28
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface FunctionId {
	/**
	 * 功能码
	 * @return
	 */
	String value();
	
	/**
	 * 对于功能码的功能描述
	 * @return
	 */
	String desc();
}
