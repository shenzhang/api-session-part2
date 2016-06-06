package com.github.shenzhang.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * User: Zhang Shen
 * Date: 5/23/16
 * Time: 11:22 PM.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-api")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/users.*"))
                .build();
    }

    @Bean
    public Docket zipkinApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("zipkin-api")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/zipkin.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Session - Swagger Demo")
                .description("Spring REST Sample with Swagger")
                .termsOfServiceUrl("http://www.thoughtworks.com")
                .contact("Zhang Shen")
                .license("Apache License Version 2.0")
//                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
}
