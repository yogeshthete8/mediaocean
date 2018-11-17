package com.ors.retailshop.config;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class SwaggerConfig {

  /**
   * Swagger docket configuration.
   */
  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2).directModelSubstitute(LocalDate.class, java.sql.Date.class)
        .directModelSubstitute(LocalTime.class, String.class).apiInfo(apiInfo()).select()
        .apis(RequestHandlerSelectors.basePackage("com.ors.retailshop")).build();
  }


  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Online Retail Shop")
        .description("Service for Online Retail Shop API.").version("1.0").build();
  }
}
