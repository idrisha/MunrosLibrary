package com.xdesign.munro.library.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@EnableSwagger2
@Configuration
@Import( BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig
{
    private static final String TITLE = "Munro Library WebClient";
    private static final String DESCRIPTION = "REST services for Munro Library";

    @Bean
    public Docket general() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Server General")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(pathsListPredicateNot())
                .build()
                .pathMapping("/")
                .apiInfo(new ApiInfoBuilder()
                        .version("1.0")
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .build());
    }

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API Version 1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xdesign.munro.library.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .version("1.0")
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .build());
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder
                .builder()
                .supportedSubmitMethods(UiConfiguration.Constants.NO_SUBMIT_METHODS)
                .build();
    }

    private Predicate<String> pathsListPredicateNot() {
        return not(PathSelectors.regex("/munro/.*"));
    }

    public static <R> Predicate<R> not(Predicate<R> predicate) {
        return predicate.negate();
    }
}
