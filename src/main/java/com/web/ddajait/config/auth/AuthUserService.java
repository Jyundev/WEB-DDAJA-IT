package com.web.ddajait.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.model.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    // email 이 아이디므로 email을 기준으로 load
    public UserDetails loadUserByUsername(String email)  {
        // TODO Auto-generated method stub
        log.info("[AuthUserService] Start: " + email);

        UserEntity userEntity = userRepository.findByEmail(email);

        UserDto userDto = new UserDto();

        userDto.setAge(userEntity.getAge());
        userDto.setEmail(userEntity.getEmail());
        userDto.setGender(userEntity.getGender());
        userDto.setInterest(userEntity.getInterest());
        userDto.setIsLogin(userDto.getIsLogin());
        userDto.setJob(userEntity.getJob());
        userDto.setNickname(userEntity.getNickname());
        userDto.setPassword(userEntity.getPassword());
        userDto.setProfileImage(userEntity.getProfileImage());
        userDto.setQualifiedCertificate(userEntity.getQualifiedCertificate());
        userDto.setTier(userEntity.getTier());
        userDto.setUserId(userEntity.getUserId());
        userDto.setRole(userEntity.getRole());


        // username의 데이터가 database에 존재함
        return new AuthUserDto(userDto);
    }
}
