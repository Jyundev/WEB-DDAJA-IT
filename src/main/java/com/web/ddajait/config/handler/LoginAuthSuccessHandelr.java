package com.web.ddajait.config.handler;

import java.io.IOException;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.web.ddajait.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginAuthSuccessHandelr extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info("[LoginAuthSuccessHandler][onAuthenticationSuccess] Start");
        // 로그인 성공시, 로그인 유무 저장
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // userService.updateIsLoginByEmail(userDetails.getUsername(), true);
   
        String redirectUrl = "/close";

        response.sendRedirect(redirectUrl);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
