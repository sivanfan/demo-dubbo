package com.ule.demo.service.common;


import com.ule.demo.common.springside.AbstractEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.io.Serializable;

/**
 * @Author fanxl
 * @Description 继承的这2个里面就包含了crud、以及分页、条件查询的所有组件了
 * @Date 17:09 2019/1/28
 **/
public interface BaseMapper<T extends AbstractEntity, ID extends Serializable> extends Mapper<T>, MySqlMapper<T>{
}
