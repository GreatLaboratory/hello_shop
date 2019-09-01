package com.example.jpashop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.jpashop"))
                .paths(PathSelectors.any()).build();
        //com.example.board패키지 내에 request매핑으로 할당된 모든 url을 선택하고
        // paths에선 특정 경로없이 전부 any()로 모든 url을 선택함
        // localhost:8080/swagger-ui.html
    }
}
