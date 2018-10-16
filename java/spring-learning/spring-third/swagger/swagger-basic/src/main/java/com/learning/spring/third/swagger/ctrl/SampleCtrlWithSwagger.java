package com.learning.spring.third.swagger.ctrl;

import com.learning.spring.third.swagger.bean.BeanWithSwaggerRequest;
import com.learning.spring.third.swagger.bean.BeanWithSwaggerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Api：用在类上，说明该类的作用。
 *
 * @ApiOperation：用在类上，说明该方法的作用。。
 */

@RestController
@Api(description = "sample swagger 测试 ctrl")
public class SampleCtrlWithSwagger {

    @RequestMapping(value = "/test/model", method = RequestMethod.POST)
    @ApiOperation("swagger 测试api method")
    public BeanWithSwaggerResponse beanWithSwagger(@RequestBody BeanWithSwaggerRequest beanWithSwaggerRequest) {
        return new BeanWithSwaggerResponse(1, "Kevin", "18");
    }
}
