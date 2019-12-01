package com.mybatislearning.city.mapper;

import com.mybatislearning.city.entity.CityPo;
import com.mybatislearning.city.mapper.provider.CityProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface CityMapper {
    @Results(id = "cityResultMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "c_name", property = "cName"),
            @Result(column = "state", property = "state"),
            @Result(column = "country", property = "country")
    })
    @SelectProvider(type = CityProvider.class, method = "selectByPrimaryKey")
    CityPo selectByPrimaryKey(Integer id);

    @UpdateProvider(type = CityProvider.class, method = "updateSelectiveByPrimaryKey")
    void updateSelectiveByPrimaryKey(CityPo cityPo);
}
