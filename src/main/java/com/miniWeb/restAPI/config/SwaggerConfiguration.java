package com.miniWeb.restAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                // 하단 경로의 API를 문서화
                .apis(RequestHandlerSelectors.basePackage("com.miniWeb.restAPI.controller"))
                .paths(PathSelectors.any())
            //   하단의 경로만 문서화할 수 있음
            //  .paths(PathSelectors.ant("/v1/**"))
                .build()
                .useDefaultResponseMessages(false); // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Mini_Web")
                .description("API Description of Mini_Web_Project")
//                .license("happydaddy").licenseUrl("http://daddyprogrammer.org").version("1")
                .build();
    }
}