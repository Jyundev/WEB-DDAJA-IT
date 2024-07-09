package com.web.ddajait.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addExposedHeader("USER_ID"); // Add this line to expose the USER_ID header
        // Add allowed origins explicitly
        List<String> allowedOrigins = Arrays.asList(
                "http://localhost:5173",
                "https://df3cpyo19sfnq.cloudfront.net",
                "https://ddajait.com",
                "https://d5ki68ixw55w9.cloudfront.net",
                "http://ddjait-react-cicd.s3-website.ap-northeast-2.amazonaws.com",
                "https://d26qduhz3ubom8.cloudfront.net");
        config.setAllowedOriginPatterns(allowedOrigins);
        config.addAllowedMethod("*"); // Allow all methods
        return config;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Apply the CORS configuration for specific paths
        source.registerCorsConfiguration("/api/v1/**", buildConfig());
        source.registerCorsConfiguration("/update/challenge/**", buildConfig());
        return new CorsFilter(source);
    }
}
