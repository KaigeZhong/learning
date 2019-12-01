package com.mybatislearning.city.entity;

import com.mybatislearning.city.domain.CityBo;

public class CityPo {
    private Integer Id;
    private String cName;
    private String state;
    private String country;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CityBo toCityBo() {
        CityBo cityBo = new CityBo();
        cityBo.setId(this.getId());
        cityBo.setcName(this.getcName());
        cityBo.setState(this.getState());
        cityBo.setCountry(this.getCountry());
        return cityBo;
    }

    public static CityPo build(CityBo cityBo) {
        CityPo cityPo = new CityPo();
        cityPo.setId(cityBo.getId());
        cityPo.setcName(cityBo.getcName());
        cityPo.setState(cityBo.getState());
        cityPo.setCountry(cityBo.getCountry());
        return cityPo;
    }
}
