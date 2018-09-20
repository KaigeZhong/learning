package com.dao;


import com.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityDao {

    @Select("SELECT id, name, state, country FROM city WHERE state = #{state}")
    City findByState(String state);
}