package com.learning.spring.third.mybatis.function.transaction.service;

import com.learning.spring.third.mybatis.function.transaction.bean.City;
import com.learning.spring.third.mybatis.function.transaction.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * mysql
 */
@Service
public class CityService {

    @Autowired
    private CityDao cityDao;

    @Transactional(rollbackFor = Exception.class )
    public void updateById() {
        City city = cityDao.findByState("CA");
        System.out.println(city);

        city.setcName("San Francisco_updated_v1");
        cityDao.update(city);
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException();
        }
        System.out.println("update success: " + cityDao.findByState("CA"));
    }

    @Transactional(rollbackFor = Exception.class )
    public void updateById2() {
        City city = cityDao.findByState("CA");
        city.setcName("San Francisco_updated2");
        cityDao.update(city);
        System.out.println("update success: " + cityDao.findByState("CA"));
    }

    @Transactional(rollbackFor = Exception.class )
    public void updateByName() {
        City city = cityDao.findByState("CA");
        String oldName = city.getcName();

        city.setcName("San Francisco_updatedByName");
        cityDao.updateByName(city, oldName);
        System.out.println("update success: " + cityDao.findByState("CA"));
    }

    @Transactional(rollbackFor = Exception.class )
    public void insert() {
        City city = new City();
        city.setcName("new name");
        city.setCountry("new country");
        city.setState("new state");
        cityDao.insert(city);
        System.out.println("insert success: " + cityDao.findByState("new state"));
    }
}
