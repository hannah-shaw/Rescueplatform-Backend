package com.rescueplatform_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.rescueplatform_backend.mapper")
public class RescueplatformBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RescueplatformBackendApplication.class, args);
    }

}
