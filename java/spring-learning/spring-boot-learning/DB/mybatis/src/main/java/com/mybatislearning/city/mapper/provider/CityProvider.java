package com.mybatislearning.city.mapper.provider;

import com.mybatislearning.city.entity.CityPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Objects;

public class CityProvider {

    public String selectByPrimaryKey(@Param("id") Integer id) {
        SQL sql = new SQL();
        sql.SELECT(baseColumns());
        sql.FROM(table());
        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    public String updateSelectiveByPrimaryKey(CityPo cityPo) {
        SQL sql = new SQL();
        sql.UPDATE(table());
        if (Objects.nonNull(cityPo.getcName())) {
            sql.SET("c_name = #{cName}");
        }
        if (Objects.nonNull(cityPo.getState())) {
            sql.SET("state = #{state}");
        }
        if (Objects.nonNull(cityPo.getCountry())) {
            sql.SET("country = #{country}");
        }
        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    private String table() {
        return "city";
    }

    private String baseColumns() {
        return "id, c_name, state, country";
    }
}
