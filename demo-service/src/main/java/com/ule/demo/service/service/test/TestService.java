package com.ule.demo.service.service.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ule.demo.common.entity.CertificateInfo;
import com.ule.demo.common.entity.Test;
import com.ule.demo.common.springside.PageInfo;
import com.ule.demo.common.utils.PageUtils;
import com.ule.demo.service.mapper.CerManagerMapper;
import com.ule.demo.service.mapper.TestMapper;
import com.ule.demo.service.service.BaseService;
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
@Service("testService")
@Slf4j
public class TestService extends BaseService<Test,Long> {
    @Autowired
    private TestMapper testMapper;

    /**
     * @Author fanxl
     * @Description 分页查询
     * @Date 10:49 2019/1/29
     * @Param [pageInfo]
     * @return PageInfo
     **/
    public PageInfo getPage(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNo(), pageInfo.getPageSize());

        Page<CertificateInfo> page = (Page)testMapper.selectAll();
        return PageUtils.convertPage(page);
    }

    public Test selectSomeResult(Map map){
        return (Test)testMapper.selectByPrimaryKey(map);
    }

}
