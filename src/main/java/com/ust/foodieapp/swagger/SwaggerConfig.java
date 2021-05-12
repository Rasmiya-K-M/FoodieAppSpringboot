package com.ust.foodieapp.swagger;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postApi(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("swagger-api-FOODIE-APP").
                apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths(){
        return or(regex("/api/v1/.*"),regex("/api/v1/.*"));
    }
    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder().title("User-Service Rest API Documentation")
                .description("this is a swagger for user-service")
                .contact("168029@ust.com")
                .license("ustwave36")
                .licenseUrl("www.ustwave36.com")
                .termsOfServiceUrl("foodieApp@gmail.com")
                .version("1.0").build();
    }
}
