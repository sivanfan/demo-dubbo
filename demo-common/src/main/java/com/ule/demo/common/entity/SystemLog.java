package com.ule.demo.common.entity;

import com.ule.demo.common.springside.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "SYSTEM_LOG")
@Getter
@Setter
public class SystemLog extends AbstractEntity<Long> {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "select SEQ_SYSTEM_LOG.nextval from dual")
    private Long id;

    /**
     * 系统标识
     */
    private String systemCode;

    /**
     * 请求序列
     */
    private String reqSequence;

    /**
     * 请求业务功能码
     */
    private String functionId;

    /**
     * 请求内容
     */
    private String requestContent;

    /**
     * 响应内容
     */
    private String responseContent;

    /**
     * 请求耗时ms
     */
    private Long useTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 预留字段
     */
    private String remark;
}