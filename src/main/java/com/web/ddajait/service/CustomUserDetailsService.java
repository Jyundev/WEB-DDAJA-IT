package com.web.ddajait.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.model.repository.UserRepository;

import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

   private final UserRepository userRepository;
   private final UserService userService;

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String email) {
      log.info("[CustomUserDetailsService][loadUserByUsername] Start");

      return userRepository.findOneWithAuthoritiesByEmail(email)
            .map(user -> createUser(email, user))
            .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
   }

   private org.springframework.security.core.userdetails.User createUser(String email, UserEntity user) {
      // log.info("[CustomUserDetailsService][createUser] user : " + user);

      try {
         userService.updateIsLoginByID(user.getEmail(), true);
      } catch (ServletException e) {
         log.error("ServletException: ", e);
      } catch (Exception e) {
         log.error("Exception: ", e);
      }

      List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
            .collect(Collectors.toList());
      

      // Email이 로그인시 ID로 사용됨
      return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            grantedAuthorities);
   }
}
