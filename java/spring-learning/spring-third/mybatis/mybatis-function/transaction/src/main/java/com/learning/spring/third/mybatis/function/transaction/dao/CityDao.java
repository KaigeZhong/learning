package com.learning.spring.third.mybatis.function.transaction.dao;


import com.learning.spring.third.mybatis.function.transaction.bean.City;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CityDao {

    @Select("SELECT id, c_name, state, country FROM city WHERE state = #{state}")
    City findByState(String state);

    @Update("UPDATE city SET c_name = #{cName} WHERE id = #{id}")
    int update(City city);

    @Update("UPDATE city SET c_name = #{city.cName} WHERE c_name = #{oldName}")
    int updateByName(@Param("city") City city, @Param("oldName") String oldName);

    @Insert("INSERT INTO city (c_name, state, country) VALUES (#{cName}, #{state}, #{country});")
    int insert(City city);
}