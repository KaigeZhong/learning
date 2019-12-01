package com.mybatislearning.city.service.impl;

import com.mybatislearning.city.domain.CityBo;
import com.mybatislearning.city.repository.CityRepository;
import com.mybatislearning.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
//    @Transactional
    public void selectByPrimaryKey(Integer id) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                CityBo cityBo = cityRepository.selectByPrimaryKey(id);
                cityBo.setcName("San Francisco 2");
                cityRepository.updateSelectiveByPrimaryKey(cityBo);

            }
        });
    }
}
