package com.mybatis.xml.dao;

import com.mybatis.xml.bean.City;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityDao {

    City findByState(String state);
}