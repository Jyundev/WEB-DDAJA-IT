package com.web.ddajait.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.ddajait.config.error.custom.DuplicateMemberException;
import com.web.ddajait.model.dao.UserDao;
import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.model.entity.AuthorityEntity;
import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.service.UserService;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    // @Autowired
    // private UserDao userDao;
    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserDao userDao;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDto> getAllUsers() throws Exception {

        return userDao.getAllUsers().stream()
                .map(UserDto::from)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteUser(Long id) throws Exception {
        UserEntity entity = userDao.findById(id);
        userDao.deleteUser(entity.getUserId());
    }

    @Override
    public UserDto findByEmail(String email) throws Exception {

        UserEntity userEntity = userDao.findByEmail(email);

        UserDto userDto = new UserDto();

        // BeanUtils.copyProperties(source, target)
        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    @Override
    public UserDto findById(Long id) throws Exception {

        UserEntity userEntity = userDao.findById(id);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    // 회원가입
    @Override
    public void createMember(UserDto userDto) throws Exception {
        log.info("[UserServiceImpl][createMember]");

        // 중복회원 처리
        int emailCheck = countMemberByMemberEmail(userDto.getEmail());
        int nicknameCheck = countMemberByMemberNickname(userDto.getNickname());

        // 이메일(ID) 중복
        if (emailCheck > 0) {
            throw new DuplicateMemberException(userDto.getEmail());
        }

        // 닉네임 중복
        if (nicknameCheck > 0) {
            throw new DuplicateMemberException(userDto.getNickname());
        }

        // 권한 설정
        AuthorityEntity authority = AuthorityEntity.builder()
                .authorityName("ROLE_USER")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .isLogin(false)
                .build();

        log.info("[UserServiceImpl][createMemberr] userEntity " + userEntity);
        userDao.createMember(userEntity);

    }

    @Override
    public void updateUser(UserDto userDto) throws Exception {
        log.info("[UserServiceImpl][updateUser] Start");

        // 중복회원 처리
        int emailCheck = countMemberByMemberEmail(userDto.getEmail());
        int nicknameCheck = countMemberByMemberNickname(userDto.getNickname());

        // 이메일(ID) 중복
        if (emailCheck > 0) {
            throw new DuplicateMemberException(userDto.getEmail());
        }

        // 닉네임 중복
        if (nicknameCheck > 0) {
            throw new DuplicateMemberException(userDto.getNickname());
        }

        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(userDto, userEntity);

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

    @Override
    public void updateIsLoginByID(String ID, Boolean isLogin) throws ServletException, Exception {
        UserEntity userEntity = userDao.findByEmail(ID);
        userEntity.setIsLogin((isLogin));
        userDao.updateIsLoginByID(userEntity);
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() throws Exception {
        return UserDto.from(userDao.getMyUserWithAuthorities());

    }

    @Override
    public UserDto getUserWithAuthorities(String username) throws Exception {
        return UserDto.from(userDao.getUserWithAuthorities(username));

    }

}
