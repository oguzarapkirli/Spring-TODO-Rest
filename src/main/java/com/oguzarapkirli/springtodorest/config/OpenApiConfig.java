package com.oguzarapkirli.springtodorest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Todos API")
                        .version("v1.0.0")
                        .description("This is a sample Spring Boot RESTful service using OpenAPI 3.")
                        .termsOfService("Sample ToS: https://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
        );
    }
}
