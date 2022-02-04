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
                .build()
                //给swagger2令牌，不然测试接口太繁琐，需要登录会被拦截
                .securityContexts(securityContexts())//全局
                .securitySchemes(securitySchemes());//安全计划;

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

    private List<ApiKey> securitySchemes(){
        //设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        //令牌
        ApiKey apikey = new ApiKey("authorization","authorization","Header");
        result.add(apikey);
        return result;
    }

    private List<SecurityContext> securityContexts(){
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextBypath("/hello/.*"));
        return result;
    }

    private SecurityContext getContextBypath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }

}
