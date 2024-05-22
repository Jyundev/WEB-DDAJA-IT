package com.web.ddajait.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import lombok.RequiredArgsConstructor;

// https://blog.naver.com/seek316/223349824088
// https://colabear754.tistory.com/99
// https://velog.io/@najiexx/Spring-Boot-3%EC%97%90-Swagger-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0springdoc-openapi
// https://velog.io/@pcs/Spring-Boot-Swagger-%EC%A0%81%EC%9A%A9

// https://tg360.tistory.com/entry/Springdoc-openapi%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-Spring-Boot-%EA%B8%B0%EB%B0%98-API%EC%9D%98-%EB%AC%B8%EC%84%9C-%EC%9E%90%EB%8F%99%ED%99%94
// 
// http://localhost:8081/swagger-ui/index.html" Or "http://localhost:8081/swagger-ui-custom.html"
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        String jwtSchemeName = "bearer-token";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);


        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes(jwtSchemeName, new io.swagger.v3.oas.models.security.SecurityScheme()
                .type(Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")))
                .addSecurityItem(securityRequirement)
                .info(apiInfo());
    }
 
    private Info apiInfo() {
        return new Info()
                .title("Springdoc 테스트")
                .description("Springdoc을 사용한 Swagger UI 테스트")
                .version("1.0.0");
                
    }
}