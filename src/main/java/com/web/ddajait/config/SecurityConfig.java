package com.web.ddajait.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.web.ddajait.config.handler.LoginAuthFailureHandler;
import com.web.ddajait.config.handler.LoginAuthSuccessHandelr;
import com.web.ddajait.config.handler.LogoutAuthSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration // 스프링 설정 파일
@EnableWebSecurity // 시큐리티 설정
// @Secured 어노테이션 활성화, @PreAuthorize 어노테이션 활성화
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

        // 비밀번호 암호화에서 사용할 객체
        @Bean
        @Lazy
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Autowired
        @Lazy
        private LoginAuthSuccessHandelr loginAuthSuccessHandler;
        @Autowired
        @Lazy
        private LoginAuthFailureHandler loginAuthFailureHandler;
        @Autowired
        @Lazy
        private LogoutAuthSuccessHandler logoutAuthSuccesshandler;

        // 인증(로그인) & 인가(권한)에 대한 시큐리티 설정
        @Bean
        @Lazy
        public SecurityFilterChain finteFilterChain(HttpSecurity http) throws Exception {
                log.info("[SecurityFilterChain][finteFilterChain] Start");

                // CSRF란, Cross Site Request Forgery의 약자로,
                // 한글 뜻으로는 사이트간 요청 위조를 뜻합니다.
                // http.csrf(AbstractHttpConfigurer::disable);
                http.csrf((csrfConfig) -> csrfConfig.disable())
                                .headers((headerConfig) -> headerConfig.frameOptions(frameOptionsConfig -> // X-Frame-Options
                                                                                                           // 헤더 비활성화
                                frameOptionsConfig.disable()))
                                // 인증 & 인가 설정
                                .authorizeHttpRequests(authorize -> authorize // http request 요청에 대한 화면 접근(url path) 권한
                                                                              // 설정
                                         // 계정 로그인,아이디 찾기 등은 인증되지 않은 사용자만
                                        .requestMatchers("/public/**")
                                        .anonymous()
                                        // "/user" 와 같은 url path로 접근할 경우... 
                                        .requestMatchers("/user/**")
                                        // 인증(로그인)만 접근 가능
                                        .authenticated()
                                        // "/admin" 와 같은 url path로 접근할 경우...
                                        .requestMatchers("/admin/**")
                                        // ADMIN이라는 권한을 갖은 사용자만 접근 가능 
                                        .hasAnyAuthority("ADMIN")
                                        // 그외의 모든 url path는 누구나 접근 가능 
                                        .anyRequest().permitAll())

                                // 인증(로그인)에 대한 설정
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/loginPage") // Controller에서 로그인 페이지 url path
                                                /*
                                                 * 로그인 화면에서 form 테그의 action 주소(url path)
                                                 * Spring Security가 로그인 검증을 진행함
                                                 * Controller에서는 해당 "/login"을 만들 필요가 없음
                                                 */
                                                .loginProcessingUrl("/login")
                                                .successHandler(loginAuthSuccessHandler) // 로그인 성공시
                                                .failureHandler(loginAuthFailureHandler) // 로그인 실패시
                                                .permitAll() // 그외의 모든 url path는 누구나 접근 가능
                                )
                                // 로그아웃에 대한 설정
                                .logout(logout -> logout
                                                .logoutUrl("/logout") // 로그아웃 요청 url path
                                                .logoutSuccessHandler(logoutAuthSuccesshandler) // 로그아웃 성공시
                                                .permitAll())
                                .exceptionHandling(exceptionHandling -> exceptionHandling
                                                // 인증되지 않은 사용자가 접근했을 때 "/index"로 리디렉션합니다.
                                                .authenticationEntryPoint(
                                                                (request, response, authException) -> response
                                                                                .sendRedirect("/index")));

                // http.headers().frameOptions().disable(); // X-Frame-Options 헤더 비활성화

                // 위에서 설정한 인증 & 인가를 Spring Boot Configuration에 적용
                return http.build();
        }
}
