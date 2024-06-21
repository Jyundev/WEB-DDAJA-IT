package com.web.ddajait.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

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
                                // .servers(List.of(new Server().url("https://"))) 
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
        public GroupedOpenApi allApi() {
                return GroupedOpenApi.builder()
                                .group("모든 API")
                                .pathsToMatch("/**")
                                .build();
        }

        @Bean
        public GroupedOpenApi challengeApi() {
                return GroupedOpenApi.builder()
                                .group("챌린지 API")
                                .pathsToMatch("/api/v1/challenge/**")
                                .build();
        }

        @Bean
        public GroupedOpenApi certificateApi() {
                return GroupedOpenApi.builder()
                                .group("자격증 API")
                                .pathsToMatch("/api/v1/certificate/**")
                                .build();
        }

        @Bean
        public GroupedOpenApi adminApi() {
                return GroupedOpenApi.builder()
                                .group("관리자 API")
                                .pathsToMatch("/api/v1/admin/**")
                                .build();
        }

        @Bean
        public GroupedOpenApi publicApi() {
                return GroupedOpenApi.builder()
                                .group("PUBLIC API")
                                .pathsToMatch("/api/v1", "/api/v1/public/**", "/api/v1/auth/**")
                                .build();
        }

        @Bean
        public GroupedOpenApi envApi() {
                return GroupedOpenApi.builder()
                                .group("환경 체크 API")
                                .pathsToMatch("/hc", "/env")
                                .build();
        }

}
