package com.ule.demo.common.exceptions;

/**
 * @Author fanxl
 * @Description 通用业务异常类
 * @Date 16:47 2019/1/28
 **/
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -2676863300064393506L;

	public BusinessException() {
		super();
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public BusinessException(String code, String message, Throwable e) {
		super(message, e);
		this.code = code;
	}

	public BusinessException(Throwable e) {
		super(e);
	}
	
	/** 业务类型代码 */
	private String code;

	public String getCode() {
		return code;
	}
}
