package com.web.ddajait.config.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.web.ddajait.config.jwt.JwtFilter;
import com.web.ddajait.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutAuthSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            try {
                userService.updateIsLoginByID(userDetails.getUsername(), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // JWT 제거 
            response.setHeader(JwtFilter.AUTHORIZATION_HEADER, ""); 


        }

    }

}
