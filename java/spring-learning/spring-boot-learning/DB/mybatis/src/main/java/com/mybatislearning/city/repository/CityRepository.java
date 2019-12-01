package com.mybatislearning.city.repository;

import com.mybatislearning.city.domain.CityBo;
import com.mybatislearning.city.entity.CityPo;
import com.mybatislearning.city.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository {
    @Autowired
    private CityMapper cityMapper;

    public CityBo selectByPrimaryKey(Integer id) {
        return cityMapper.selectByPrimaryKey(id).toCityBo();
    }

    public void updateSelectiveByPrimaryKey(CityBo cityBo) {
        cityMapper.updateSelectiveByPrimaryKey(CityPo.build(cityBo));
    }
}
