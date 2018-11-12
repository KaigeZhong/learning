package com.mybatis.datasource.dao;

import com.mybatis.datasource.bean.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityDao {

    City findByState(String state);

    List<City> findbyStateFuzzy(String state);
}