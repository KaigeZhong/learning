package com.learning.spring.third.swaggeruiconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Conf {

    @Bean
    public Docket createRestApi() {
        //为每个接口添加token描述
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("token").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                //api的基本信息描述
                .apiInfo(apiInfo())
                .select()
                /*###### 指定swagger处理的接口 start ####*/
                /**
                 * 含有两类Predicate：
                 * private Predicate<RequestHandler> requestHandlerSelector
                 * private Predicate<String> pathSelector
                 * 所有的predicate会做and操作, 满足所有要求的接口swagger才会处理
                 */
                .apis(RequestHandlerSelectors.basePackage("com.learning.spring.third.swaggeruiconfig.ctrl"))//指定包下面的接口才会处理
//                .paths(Predicates.not(PathSelectors.regex("/error")))//指定路径不处理
                .paths(PathSelectors.any())
                /*###### 指定swagger处理的接口  end ####*/

                .build()
                //为每个接口添加参数描述
                .globalOperationParameters(pars)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("新潮CAS服务 RESTful API")
                .description("")
                .termsOfServiceUrl("")
                .version("2.0")
                .build();
    }
}