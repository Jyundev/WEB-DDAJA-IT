package com.web.ddajait.config.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private UserService userService;

    @Autowired
    public LoginAuthSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info("[LoginAuthSuccessHandler][onAuthenticationSuccess] Start");
        // 로그인 성공시, 로그인 유무 저장
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        try {
            userService.updateIsLoginByID(userDetails.getUsername(), true);
            log.info("[LoginAuthSuccessHandler][updateIsLoginByID] Start");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userDetails.getUsername() == null) {
            response.sendRedirect("/close");
        } else {
            HttpSession session = request.getSession();
            UserDto dto;
            try {
                log.info("[LoginAuthSuccessHandler][Session] Start : " + userDetails.getUsername());

                dto = userService.findByEmail(userDetails.getUsername());
                
                session.setAttribute("userId", dto.getUser_id());
                // session.setAttribute("userEmail", dto.getEmail());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
