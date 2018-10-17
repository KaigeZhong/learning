package com.learning.json.jackson.databind.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class) //指定Json字段名映射策略为蛇形大小写策略。缺省则直接使用Bean属性名
public class PersonWithAnnotation {
    private String pName;
    @JsonProperty(value = "user_name_withJsonPropertyAnnotation") //指定序列化时的字段名，默认使用属性名
    private String pAddress;
    @JsonInclude(JsonInclude.Include.NON_NULL) //属性为NULL则不参与序列化
    private String pMobile;
    @JsonIgnore //序列化时忽略此字段
    private String extra;


    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getpMobile() {
        return pMobile;
    }

    public void setpMobile(String pMobile) {
        this.pMobile = pMobile;
    }
}