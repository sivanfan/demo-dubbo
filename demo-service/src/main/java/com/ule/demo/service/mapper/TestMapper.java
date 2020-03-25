package com.ule.demo.service.mapper;

import com.ule.demo.common.entity.Test;
import com.ule.demo.service.common.BaseMapper;

public interface TestMapper extends BaseMapper<Test,Long> {
    /**
     *
     * @mbggenerated
     */
    Test selectByPrimaryKey(Long id);
}