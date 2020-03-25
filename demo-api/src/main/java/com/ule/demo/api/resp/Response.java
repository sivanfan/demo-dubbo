package com.ule.demo.api.resp;

import com.ule.demo.api.constants.Constants;

import java.io.Serializable;

/**
 * @ClassName Response
 * @Author fanxl
 * @Description 响应类
 * @Date 15:39  2019/1/28
 * @Version 1.0
 **/
public class Response implements Serializable {

    private static final long serialVersionUID = 3729571113820395266L;
    /** 状态码 */
    private String code;

    /** 返回信息 */
    private String msg;

    /** 返回数据 */
    private Object data;

    public Response() {
        this(Constants.CODE_SUCCESS, Constants.MSG_SUCCESS);
    }

    public Response(Object data) {
        this(Constants.CODE_SUCCESS, Constants.MSG_SUCCESS);
        this.data = data;
    }

    public Response(String code, String msg) {
        this(code, msg, null);
    }

    public Response(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response fail(String msg) {
        return new Response(Constants.CODE_FAILURE, msg);
    }
    public static Response fail(String code,String msg) {
        return new Response(code, msg);
    }

    public static Response success() {
        return new Response();
    }

    public static Response success(Object data) {
        return new Response(data);
    }

    public static Response success(String msg) {
        return new Response(msg);
    }

    public Response(String msg) {
        this(Constants.CODE_SUCCESS, msg, null);
    }

    public static Response success(String msg, Object data) {
        return new Response(msg, data);
    }

    public Response(String msg, Object data) {
        this(Constants.CODE_SUCCESS, msg, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
