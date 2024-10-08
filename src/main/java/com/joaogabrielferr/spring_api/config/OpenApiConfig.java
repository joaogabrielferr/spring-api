package com.joaogabrielferr.spring_api.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Spring API")
                        .version("v1")
                        .description("API built with Java 17 and Spring Boot 3")
                        .license(new License().name("MIT"))
                );
    }


}
