package com.web.ddajait.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.web.ddajait.config.handler.LoginAuthSuccessHandler;
import com.web.ddajait.service.UserService;

@Configuration
public class AppConfig {

    // private final UserService userService;
    // private final PasswordEncoder passwordEncoder;

    // @Autowired
    // public AppConfig(UserService userService, PasswordEncoder passwordEncoder) {
    // this.userService = userService;
    // this.passwordEncoder = passwordEncoder;
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginAuthSuccessHandler loginAuthSuccessHandler(UserService userService) {
        return new LoginAuthSuccessHandler(userService);
    }

    // @Bean
    // public AuthProvider authProvider(PasswordEncoder passwordEncoder) {
    //     return new AuthProvider(passwordEncoder);
    // }

}
