package com.web.ddajait.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.ddajait.config.constant.Role;
import com.web.ddajait.config.error.custom.DuplicateMemberException;
import com.web.ddajait.model.dao.UserDao;
import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void deleteUser(Long id) throws Exception {
        // TODO Auto-generated method stub
        UserEntity entity = userDao.findById(id);
        userDao.deleteUser(entity.getUserId());
    }

    @Override
    public UserDto findByEmail(String email) throws Exception {

        UserEntity userEntity = userDao.findByEmail(email);

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

        return userDto;
    }

    @Override
    public UserDto findById(Long id) throws Exception {

        UserEntity userEntity = userDao.findById(id);

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

        return userDto;
    }

    // 회원가입
    @Override
    public void createMemberr(UserDto dto) throws Exception {
        log.info("[UserServiceImpl][createMemberr] Start");

        // 중복회원 처리
        int emailCheck = countMemberByMemberEmail(dto.getEmail());
        int nicknameCheck = countMemberByMemberNickname(dto.getNickname());

        // 이메일(ID) 중복 
        if (emailCheck > 0 ) {
            throw new DuplicateMemberException(dto.getEmail());
        }

        // 닉네임 중복 
        if (nicknameCheck > 0 ) {
            throw new DuplicateMemberException(dto.getNickname());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setAge(dto.getAge());
        userEntity.setEmail(dto.getEmail());
        userEntity.setGender(dto.getGender());
        userEntity.setInterest(dto.getInterest());
        userEntity.setIsLogin(dto.getIsLogin());
        userEntity.setJob(dto.getJob());
        userEntity.setNickname(dto.getNickname());
        userEntity.setPassword(dto.getPassword());
        userEntity.setProfileImage(dto.getProfileImage());
        userEntity.setQualifiedCertificate(dto.getQualifiedCertificate());
        userEntity.setTier(dto.getTier());
        userEntity.setUserId(dto.getUserId());
        userEntity.setRole(dto.getRole());

        // 인가 : 권한 설정
        // 사용자의 닉네임이 ROLE_ADMIN인 경우 관리자로 인식
        if (dto.getNickname().equals(Role.ADMIN.getKey())) {
            userEntity.setRole(Role.ADMIN.name());
        }

        log.info("[UserServiceImpl][createMemberr] dto " + dto);

        // 비밀번호 암호화 적용
        String rawPwd = userEntity.getPassword();
        String encodedPwd = bCryptPasswordEncoder.encode(rawPwd);
        userEntity.setPassword(encodedPwd);

        log.info("[UserServiceImpl][createMemberr] userEntity " + userEntity);

        userDao.createMemberr(userEntity);

    }

    @Override
    public void updateUser(UserDto dto) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(dto.getAge());
        userEntity.setEmail(dto.getEmail());
        userEntity.setGender(dto.getGender());
        userEntity.setInterest(dto.getInterest());
        userEntity.setIsLogin(dto.getIsLogin());
        userEntity.setJob(dto.getJob());
        userEntity.setNickname(dto.getNickname());
        userEntity.setPassword(dto.getPassword());
        userEntity.setProfileImage(dto.getProfileImage());
        userEntity.setQualifiedCertificate(dto.getQualifiedCertificate());
        userEntity.setTier(dto.getTier());
        userEntity.setUserId(dto.getUserId());
        userEntity.setRole(dto.getRole());

        userDao.updateUser(userEntity);
    }

    @Override
    public int countMemberByMemberEmail(String email) throws Exception {
        return userDao.countMemberByMemberEmail(email);
    }

    @Override
    public int countMemberByMemberNickname(String nickname) throws Exception {
        return userDao.countMemberByMemberNickname(nickname);
    }

}
