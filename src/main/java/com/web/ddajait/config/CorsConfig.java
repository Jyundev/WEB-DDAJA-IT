package com.web.ddajait.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOriginPattern("http://localhost:5173"); 
        config.addAllowedOriginPattern("https://ddajait.com/");
        config.addAllowedOriginPattern("https://ddajait.shop/"); 

        config.addExposedHeader("USER_ID"); // Add this line to expose the USER_ID header
        source.registerCorsConfiguration("/api/v1/**", config);
        source.registerCorsConfiguration("/update/challenge/**", config);

        return new CorsFilter(source);
    }
}
