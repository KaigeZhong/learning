package com.learning.spring.third.swagger.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "extra info")
public class ExtInfo {
    @ApiModelProperty("兴趣")
    private String interest;

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
