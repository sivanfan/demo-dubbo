package com.ule.demo.common.constants;
import java.io.Serializable;
/**
 * @ClassName BaseCode
 * @Author fanxl
 * @Description //TODO
 * @Date 15:53  2019/2/11
 * @Version 1.0
 **/
public interface BaseCode extends Serializable {
    /**
     *	获取状态码
     * @author fanxl
     * @date 11:04 2019/3/15
     * @param
     * @return
     */
    String getCode();
    /**
     *	获取描述
     * @author fanxl
     * @date 11:04 2019/3/15
     * @param
     * @return
     */
    String getDesc();
}

