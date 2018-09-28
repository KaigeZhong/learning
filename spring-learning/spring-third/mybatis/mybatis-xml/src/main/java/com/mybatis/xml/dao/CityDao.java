package com.mybatis.xml.dao;

import com.mybatis.xml.bean.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityDao {

    City findByState(String state);

    List<City> findbyStateFuzzy(String state);
}