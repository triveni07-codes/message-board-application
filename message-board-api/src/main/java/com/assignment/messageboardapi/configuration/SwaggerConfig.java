package com.assignment.messageboardapi.configuration;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value("${service.version}")
  private String version;

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("MESSAGE BOARD API")
        .description("Java backend api for message board management")
        .version(version).build();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .paths(Predicates.not(PathSelectors.regex("/error.*")))
        .paths(Predicates.not(PathSelectors.regex("/health.*")))
        .paths(Predicates.not(PathSelectors.regex("/info.*")))
        .build();
  }
}