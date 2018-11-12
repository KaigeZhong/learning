package com.mybatis.datasource.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DualDao {

    @Select("select 'dual test' from dual")
    String test();
}

