package com.salary.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //swagger的Docket
    @Bean
    public Docket docket(){
        ParameterBuilder token = new ParameterBuilder();
        ParameterBuilder login_name = new ParameterBuilder();

        //token请求头
        List<Parameter> pars = new ArrayList<>();
        token.name("token").description("认证令牌")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的token参数非必填，传空也可以

        //login_name请求头
        login_name.name("login_name").description("登录名")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();

        pars.add(token.build());
        pars.add(login_name.build());  //根据每个方法名也知道当前方法在设置什么参数
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())// 对所有api进行监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    //配置swagger信息
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact =  new Contact("liang" + "", "", "1982989137@qq.com");
        return new ApiInfo(
                "职工工资管理系统Api文档",
                "有关职工工资管理的Api接口文档",
                "2.0",
                "",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
