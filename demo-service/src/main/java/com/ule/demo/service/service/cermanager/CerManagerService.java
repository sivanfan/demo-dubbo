package com.ule.demo.service.service.cermanager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ule.demo.common.utils.PageUtils;
import com.ule.demo.service.mapper.CerManagerMapper;
import com.ule.demo.service.service.BaseService;
import com.ule.demo.common.entity.CertificateInfo;
import com.ule.demo.common.springside.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Author fanxl
 * @Description
 * @Date 9:50 2019/3/15
 * @Param 
 * @return 
 */
@Service("cerManagerService")
@Slf4j
public class CerManagerService extends BaseService<CertificateInfo,Long> {
    @Autowired(required = false)
    private CerManagerMapper cerManagerMapper;

    /**
     * @Author fanxl
     * @Description 分页查询
     * @Date 10:49 2019/1/29l
     * @Param [pageInfo]
     * @return PageInfo
     **/
    public PageInfo getPage(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNo(), pageInfo.getPageSize());
        Example example = new Example(CertificateInfo.class);
        CertificateInfo info=(CertificateInfo)pageInfo.getQueryParam();
        example.createCriteria().andEqualTo(info);
        example.setOrderByClause(info.getSort() + " "+info.getOrder());
        Page<CertificateInfo> page = (Page)cerManagerMapper.selectByExample(example);
        return PageUtils.convertPage(page);
    }
    /**
     * @Author fanxl
     * @Description 根据条件查询集合
     * @Date 10:52 2019/1/29
     *
     * @param certificateInfo
     * @return java.util.List<CertificateInfo>
     **/
    public List<CertificateInfo> queryList(CertificateInfo certificateInfo) {
        Example example = new Example(CertificateInfo.class);
        Example.Criteria criteria =  example.createCriteria();
        criteria.andEqualTo(certificateInfo);
        return cerManagerMapper.selectByExample(example);
    }

    public List<CertificateInfo> selectSomeResult(Map map){
        return cerManagerMapper.selectSomeResult(map);
    }

    public Integer getRefundAmount(Map map){
        return cerManagerMapper.getRefundAmount(map);
    }

    public Map getRefundAmountAndPoint(Map map){
        return cerManagerMapper.getRefundAmountAndPoint(map);
    }
}
