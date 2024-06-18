package com.web.ddajait.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.CorsFilter;

import com.web.ddajait.config.auth.AuthenticatedMatchers;
import com.web.ddajait.config.constant.Role;
import com.web.ddajait.config.handler.LogoutAuthSuccessHandler;
import com.web.ddajait.config.jwt.JwtAccessDeniedHandler;
import com.web.ddajait.config.jwt.JwtAuthenticationEntryPoint;
import com.web.ddajait.config.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration // 스프링 설정 파일
@EnableWebSecurity // 시큐리티 설정
// @Secured 어노테이션 활성화, @PreAuthorize 어노테이션 활성화
// @EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

        // JWT 관련 빈
        private final TokenProvider tokenProvider;
        private final CorsFilter corsFilter;
        private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

        private final LogoutAuthSuccessHandler logoutAuthSuccessHandler;

        // 비밀번호 암호화에서 사용할 객체
        // @Bean
        // public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        // }

        // 인증(로그인) & 인가(권한)에 대한 시큐리티 설정
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
                log.info("[SecurityFilterChain][filterChain] Start");

                httpSecurity
                                // CSRF 비활성화
                                .csrf(csrf -> csrf.disable())
                                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                                // 예외 처리 설정
                                .exceptionHandling(exceptionHandling -> exceptionHandling
                                                // 인증되지 않은 사용자가 접근했을 때 401에러 발생
                                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                                // 접근이 거부되었을 때 403에러 발생
                                                .accessDeniedHandler(jwtAccessDeniedHandler))
                                // enable h2-console
                                .headers(headers -> headers
                                                // X-Frame-Options 헤더 비활성화
                                                .frameOptions(frameOptions -> frameOptions.disable()))
                                // 세션을 사용하지 않고, stateless 서버 생성
                                .sessionManagement(sessionManagement -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // 인증 & 인가 설정
                                .authorizeHttpRequests(authorize -> authorize
                                                // 계정 로그인, 아이디 찾기 등은 인증되지 않은 사용자만 접근 가능
                                                .requestMatchers(AuthenticatedMatchers.ignoringArray).permitAll()
                                                .requestMatchers("/public/**", "/api/v1/public/**").anonymous()
                                                // "/user" 와 같은 URL path로 접근할 경우 인증(로그인)만 접근 가능
                                                .requestMatchers("/user/**", "/api/v1/user/**").authenticated()
                                                // "/admin" 와 같은 URL path로 접근할 경우 ADMIN 권한을 갖은 사용자만 접근 가능
                                                .requestMatchers("/admin/**", "/api/v1/admin/**").hasAuthority(Role.ADMIN.getKey())
                                                // "/user/challenge" 와 같은 URL path로 접근할 경우 CHALLENGER 권한을 갖은 사용자만 접근 가능
                                                .requestMatchers("/user/challenge/**", "/api/v1/user/challenge/**").hasAuthority(Role.CHALLENGER.getKey())
                                                // AuthenticatedMatchers URL -> 로그인 api는 누구나 접근 가능
                                                .requestMatchers("/api/v1/auth/authenticate", "/api/v1/public/join")
                                                .permitAll() // 로그인 api
                                                // 그 외의 모든 URL path는 누구나 접근 가능
                                                .anyRequest().permitAll())
                                // 로그아웃에 대한 설정
                                .logout(logout -> logout
                                                .logoutUrl("/api/v1/logout") // 로그아웃 요청 URL path
                                                .logoutSuccessHandler(logoutAuthSuccessHandler) // 로그아웃 성공시
                                                .permitAll())
                                .with(new JwtSecurityConfig(tokenProvider), customizer -> {
                                });
                                
                return httpSecurity.build();

        }

        @Bean
        public HttpSessionEventPublisher httpSessionEventPublisher() {
                return new HttpSessionEventPublisher();
        }

}
