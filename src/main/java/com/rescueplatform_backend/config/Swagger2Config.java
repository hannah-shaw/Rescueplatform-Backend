package com.rescueplatform_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean//规定扫描哪些包下面生成swagger2文档
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //选择扫面哪个包
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rescueplatform_backend.controller"))
                //所有的路径都可以
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //标题
                .title("应急救援信息管理平台接口文档")
                //描述
                .description("应急救援信息管理平台接口文档 作者:xiaohan")
                .contact(new Contact("xxxx","http:localhost:8081/doc.html","13107675919@163.com"))
                .version("1.0")
                .build();
    }

}
