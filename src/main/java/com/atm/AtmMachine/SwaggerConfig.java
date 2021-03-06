package com.atm.AtmMachine;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()     
                .apis(RequestHandlerSelectors.basePackage("com.atm"))
                .paths(regex("/atm.*"))
                .build()
                .apiInfo(metaData());

    }
    
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API For ATM Service",
                "Spring Boot REST API for withdrawing money from ATM",
                "1.0",
                "Terms of service",
                new Contact("Nithya Periyasamy", "", "nityaperiyasamy@gmail.com"),"","" );
        return apiInfo;
    }
}