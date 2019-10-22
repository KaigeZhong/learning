package com.component.factorybean;

import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.stereotype.Component;

@Component("fBean")
public class LFactoryBean implements SmartFactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new FBean();
    }

    @Override
    public Class<LFactoryBean> getObjectType() {
        return LFactoryBean.class;
    }

    @Override
    public boolean isEagerInit() {
        return true;
    }
}
