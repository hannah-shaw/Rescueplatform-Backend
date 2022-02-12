package com.rescueplatform_backend.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: yeb
 * @description: 分页插件配置
 * @author: Honors
 * @create: 2021-07-20 09:28
 */
@Configuration
public class MybatisPlusPage {

    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return new PaginationInterceptor();
    }
}