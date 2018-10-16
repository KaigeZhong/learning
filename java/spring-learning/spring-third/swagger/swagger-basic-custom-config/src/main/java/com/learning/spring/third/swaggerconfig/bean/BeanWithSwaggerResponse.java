package com.learning.spring.third.swaggerconfig.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ApiModel：描述一个Model的信息
 *
 *       @ApiModelProperty：描述一个model的属性
 */
@ApiModel(description = "swagger 测试response model")
public class BeanWithSwaggerResponse {
    @ApiModelProperty(value = "id", required = true)
    private Integer id;
    @ApiModelProperty(value = "名字", required = true, allowEmptyValue = true)
    private String name;
    @ApiModelProperty(value = "年龄")
    private String age;
    public BeanWithSwaggerResponse(Integer id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
