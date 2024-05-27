package com.web.ddajait.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

/**
 * 참고 링크
 * 기본설정
 * https://blog.naver.com/seek316/223349824088
 * https://colabear754.tistory.com/99
 * security 설정
 * https://velog.io/@zinna_1109/Toy-Project-Swagger-%EB%8F%84%EC%9E%85-%EC%8B%9C-Spring-Security-%EC%84%A4%EC%A0%95
 * 
 * Swagger 접속 링크
 * http://localhost:8081/swagger-ui/index.html
 */

 @Configuration
 public class SwaggerConfiguration {
 
     private static final String SCHEME_NAME = "bearerAuth";
     private static final String SCHEME = "bearer";
     private static final String BEARER_FORMAT = "JWT";
 
     @Bean
     public OpenAPI customOpenAPI() {
         return new OpenAPI()
                 .info(new Info().title("API").version("v1"))
                 .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                 .components(new Components()
                         .addSecuritySchemes(SCHEME_NAME,
                                 new SecurityScheme()
                                         .type(SecurityScheme.Type.HTTP)
                                         .scheme(SCHEME)
                                         .bearerFormat(BEARER_FORMAT)
                                         .in(SecurityScheme.In.HEADER)
                                         .name("Authorization")));
     }
 
     @Bean
     public GroupedOpenApi publicApi() {
         return GroupedOpenApi.builder()
                 .group("public")
                 .pathsToMatch("/**")
                 .build();
     }
 }

// @Configuration
// @RequiredArgsConstructor
// public class SwaggerConfig {
    // @Bean
    // public OpenAPI openAPI() {
    //     String jwtSchemeName = "bearer-token";
    //     SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

    //     return new OpenAPI()
    //             .components(new Components()
    //                     .addSecuritySchemes(jwtSchemeName, new io.swagger.v3.oas.models.security.SecurityScheme()
    //                             .type(Type.HTTP)
    //                             .scheme("bearer")
    //                             .bearerFormat("JWT")))
    //             .addSecurityItem(securityRequirement)
    //             .info(apiInfo());
    // }

    // @Bean
    // public OpenAPI customOpenAPI() {
    //     return new OpenAPI()
    //             .info(new Info().title("API").version("v1"))
    //             .components(new Components()
    //                     .addSecuritySchemes("bearer-key",
    //                             new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
    //                                     .bearerFormat("JWT")))
    //             .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
    //             .info(apiInfo());
    // }

    // private Info apiInfo() {
    //     return new Info()
    //             .title("Springdoc 테스트")
    //             .description("Springdoc을 사용한 Swagger UI 테스트")
    //             .version("1.0.0");

    // }

// }