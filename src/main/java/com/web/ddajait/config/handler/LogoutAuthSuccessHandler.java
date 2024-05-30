package com.web.ddajait.config.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.web.ddajait.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
        // TODO Auto-generated method stub

        if (authentication == null) {
            response.sendRedirect("/index");
        } else {
            try {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                userService.updateIsLoginByID(userDetails.getUsername(), false);
                HttpSession session = request.getSession();
                session.removeAttribute("userId");
                session.removeAttribute("userEmail");

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
