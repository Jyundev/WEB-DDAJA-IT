package com.web.ddajait.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.web.ddajait.config.auth.AuthenticatedMatchers;
import com.web.ddajait.config.handler.LoginAuthFailureHandler;
import com.web.ddajait.config.handler.LoginAuthSuccessHandler;
import com.web.ddajait.config.handler.LogoutAuthSuccessHandler;
import com.web.ddajait.config.jwt.JwtAccessDeniedHandler;
import com.web.ddajait.config.jwt.JwtAuthenticationEntryPoint;
import com.web.ddajait.config.jwt.JwtFilter;
import com.web.ddajait.config.jwt.TokenProvider;

import lombok.AllArgsConstructor;
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

        private final LoginAuthSuccessHandler loginAuthSuccessHandler;
        private final LoginAuthFailureHandler loginAuthFailureHandler;
        private final LogoutAuthSuccessHandler logoutAuthSuccessHandler;
        private final PasswordEncoder passwordEncoder;

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
                                                .requestMatchers("/public/**", "/api/v1/public/**").anonymous()
                                                // "/user" 와 같은 URL path로 접근할 경우 인증(로그인)만 접근 가능
                                                .requestMatchers("/user/**", "/api/v1/user/**").authenticated()
                                                // "/admin" 와 같은 URL path로 접근할 경우 ADMIN 권한을 갖은 사용자만 접근 가능
                                                .requestMatchers("/admin/**", "/api/v1/admin/**")
                                                .hasAnyAuthority("ADMIN")
                                                // AuthenticatedMatchers URL은 누구나 접근 가능
                                                .requestMatchers(AuthenticatedMatchers.ignoringArray).permitAll()
                                                // 그 외의 모든 URL path는 누구나 접근 가능
                                                .anyRequest().permitAll())
                                // 인증(로그인)에 대한 설정
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/loginPage") // Controller에서 로그인 페이지 URL path
                                                /*
                                                 * 로그인 화면에서 form 태그의 action 주소(URL path)
                                                 * Spring Security가 로그인 검증을 진행함D
                                                 * Controller에서는 해당 "/login"을 만들 필요가 없음
                                                 */
                                                .loginProcessingUrl("/login")
                                                .successHandler(loginAuthSuccessHandler) // 로그인 성공시
                                                .failureHandler(loginAuthFailureHandler) // 로그인 실패시
                                                .permitAll() // 그 외의 모든 URL path는 누구나 접근 가능
                                )
                                // 로그아웃에 대한 설정
                                .logout(logout -> logout
                                                .logoutUrl("/logout") // 로그아웃 요청 URL path
                                                .logoutSuccessHandler(logoutAuthSuccessHandler) // 로그아웃 성공시
                                                .permitAll())
                                // JwtFilter
                                .with(new JwtSecurityConfig(tokenProvider), customizer -> {});


                return httpSecurity.build();

        }


}
