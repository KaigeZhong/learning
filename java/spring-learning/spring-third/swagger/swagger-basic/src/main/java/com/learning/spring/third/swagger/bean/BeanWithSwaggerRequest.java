package com.learning.spring.third.swagger.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ApiModel：描述一个Model的信息
 *
 *       @ApiModelProperty：描述一个model的属性
 */
@ApiModel(description = "swagger 测试request model")
public class BeanWithSwaggerRequest {
    @ApiModelProperty(value = "id", required = true)
    private Integer id;
    @ApiModelProperty(value = "名字", required = true, allowEmptyValue = true)
    private String name;

    public BeanWithSwaggerRequest(Integer id, String name, String age) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
