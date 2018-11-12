package com.mybatis.datasource.bean;

import java.io.Serializable;

public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String CName;
    private String state;
    private String country;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCName() {
        return this.CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return getId() + "," + getCName() + "," + getState() + "," + getCountry();
    }

}