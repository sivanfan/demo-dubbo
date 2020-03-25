package com.ule.demo.service.mapper;


import com.ule.demo.common.entity.CertificateInfo;
import com.ule.demo.service.common.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author fanxl
 * @Description 证书管理模块mapper
 * @Date 11:13 2019/2/21
 */
public interface CerManagerMapper extends BaseMapper<CertificateInfo,Long> {
    //简单的增删改查 可以不需要自己实现，如果是复杂的sql比如多表复杂操作，则需要自己实现方法，同时在resource/mapper下增加CerManagerMapper.xml 在里面进行sql实现
    //List<CertificateInfo> selectSomeResult(String czpayId);

    @Select("SELECT * FROM cermanager.certificate_info t where t.CER_TYPE=#{cerType} and t.CER_ENV=#{cerEnv}")
    List<CertificateInfo> selectSomeResult(Map map);

    @Select("SELECT SUM(t.CER_ENV) FROM certificate_info t where t.CER_TYPE=#{cerType} and t.CER_ENV=#{cerEnv}")
    Integer getRefundAmount(Map query);

    @Select("SELECT SUM(t.CER_ENV) as sumCerEnv , SUM(t.CER_TYPE) as sumCerType FROM certificate_info t where t.CER_TYPE=#{cerType} and t.CER_ENV=#{cerEnv}")
    Map getRefundAmountAndPoint(Map query);
}