package com.service;

import com.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisService {

    @Autowired
    TestDao testDao;

    public void mybatisTest() {
        System.out.println(testDao.test());
    }
}
