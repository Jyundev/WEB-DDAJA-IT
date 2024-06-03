package com.web.ddajait.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.ddajait.config.constant.Role;
import com.web.ddajait.config.error.custom.DuplicateMemberException;
import com.web.ddajait.config.error.custom.NotFoundMemberException;
import com.web.ddajait.model.dao.CertificateInfoDao;
import com.web.ddajait.model.dao.ChallengeInfoDao;
import com.web.ddajait.model.dao.UserCertificateDao;
import com.web.ddajait.model.dao.UserDao;
import com.web.ddajait.model.dao.UserchallengeDao;
import com.web.ddajait.model.dto.UserCertificateDto;
import com.web.ddajait.model.dto.UserChallengeDto;
import com.web.ddajait.model.dto.UserDto;
import com.web.ddajait.model.entity.AuthorityEntity;
import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.model.entity.ChallengeInfoEntity;
import com.web.ddajait.model.entity.UserCertificateEntity;
import com.web.ddajait.model.entity.UserChallengeEntity;
import com.web.ddajait.model.entity.UserEntity;
import com.web.ddajait.service.UserService;
import com.web.ddajait.util.EntityUtil;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ChallengeInfoDao challengeInfoDao;
    private final CertificateInfoDao certificateInfoDao;
    private final UserCertificateDao userCertificateDao;
    private final UserchallengeDao userchallengeDao;

    private final PasswordEncoder bCryptPasswordEncoder;
    private final HttpSession httpSession;

    public UserServiceImpl(UserDao userDao,
            UserCertificateDao userCertificateDao,
            UserchallengeDao UserchallengeDao,
            CertificateInfoDao certificateInfoDao,
            ChallengeInfoDao challengeInfoDao,
            PasswordEncoder bCryptPasswordEncoder, HttpSession httpSession) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userCertificateDao = userCertificateDao;
        this.certificateInfoDao = certificateInfoDao;
        this.challengeInfoDao = challengeInfoDao;
        this.userchallengeDao = UserchallengeDao;
        this.httpSession = httpSession;
    }

    @Override
    public List<UserDto> getAllUsers() throws Exception {

        return userDao.getAllUsers().stream()
                .map(UserDto::from)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteUser(Long id) throws Exception {
        Optional<UserEntity> entity = userDao.findById(id);
        if (entity.isPresent()) {
            userDao.deleteUser(entity.get().getUser_id());

        } else {
            throw new EntityNotFoundException("UserEntity Not Found");
        }
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

        Optional<UserEntity> userEntity = userDao.findById(id);

        if (userEntity.isPresent()) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            return userDto;

        } else {
            throw new EntityNotFoundException("UserEntity Not Found");

        }
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
        Set<AuthorityEntity> authorities = new HashSet<>();

        if (userDto.getNickname().equals(Role.ADMIN.name())) {

            log.info("[UserServiceImpl][createMember] : " + userDto.getNickname());
            authorities.add(AuthorityEntity.builder()
                    .authorityName(Role.ADMIN.getKey())
                    .build());
            authorities.add(AuthorityEntity.builder()
                    .authorityName(Role.USER.getKey())
                    .build());
        } else {
            authorities.add(AuthorityEntity.builder()
                    .authorityName(Role.USER.getKey())
                    .build());
        }
        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(authorities)
                .isLogin(false)
                .build();

        log.info("[UserServiceImpl][createMemberr] userEntity " + userEntity);

        httpSession.setAttribute("userId", userEntity.getUser_id());

        userDao.createMember(userEntity);

    }

    // 프로필 수정
    @Override
    @Transactional
    public void updateUser(UserDto userDto, String email) throws Exception {
        log.info("[UserServiceImpl][updateUser] Start");

        // 기존 사용자 정보 가져오기
        if (userDao.findByEmail(email) != null) {
            log.info("[UserServiceImpl][updateUser] email : " + email);

            UserEntity userEntity = userDao.findByEmail(email);

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

            // 비밀번호 암호화
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

            // userDto 속성중 Null값이 아닌 값만 userEntity로 복사
            EntityUtil.copyNonNullProperties(userDto, userEntity);

            log.info("[UserServiceImpl][updateUser] userDto : " + userDto.getNickname());
            log.info("[UserServiceImpl][updateUser] userEntity : " + userEntity.getNickname());
            log.info("[UserServiceImpl][updateUser] userEntity : " + userEntity.getUser_id());

            userDao.updateUser(userEntity);
        }
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
        log.info("[UserServiceImpl][getMyUserWithAuthorities] Start ");
        UserDto userDto = UserDto.from(userDao.getMyUserWithAuthorities());
        return userDto;

    }

    @Override
    public UserDto getUserWithAuthorities(String username) throws Exception {
        log.info("[UserServiceImpl][getUserWithAuthorities] Start ");
        return UserDto.from(userDao.getUserWithAuthorities(username));

    }

    // 유저 자격증

    @Override
    public List<UserCertificateDto> getUserCertificateList() throws Exception {
        log.info("[UserServiceImpl][getUserCertificate] Starts");
        Long user_id = (Long) httpSession.getAttribute("userId");

        if (user_id == null) {
            throw new NotFoundMemberException();
        }

        return userCertificateDao.findUserCertificateByUserId(user_id).stream()
                .map(UserCertificateDto::from)
                .collect(Collectors.toList());

    }

    @Override
    public void updateUserCertificate(UserCertificateDto dto, Long userCertificateId) throws Exception {
        log.info("[UserServiceImpl][updateUserCertificate] Starts");

        if (userCertificateDao.findUserCertificateByUserId(userCertificateId) != null) {
            UserCertificateEntity entity = userCertificateDao.findUserCertificateById(userCertificateId);
            log.info("[UserServiceImpl][updateUserCertificate] entity " + entity);

            userCertificateDao.updateUserrCertificate(entity);
        } else {
            throw new NotFoundMemberException();
        }

    }

    @Override
    public void inserteUserCertificate(UserCertificateDto dto) throws Exception {
        log.info("[UserServiceImpl][inserteUserCertificate] Starts");

        UserCertificateEntity entity = new UserCertificateEntity();

        Long user_id = (Long) httpSession.getAttribute("userId");

        if (user_id != null) {
            dto.setUser_id(user_id);
            EntityUtil.copyNonNullProperties(dto, entity);
            userCertificateDao.insertUserrCertificate(entity);
        } else {
            throw new NotFoundMemberException();
        }

    }

    // 유저 챌린지

    @Override
    public List<UserChallengeDto> getUserChallengList() throws Exception {
        log.info("[UserServiceImpl][getUserChalleng] Starts");

        Long user_id = (Long) httpSession.getAttribute("userId");
        if (user_id == null) {
            throw new NotFoundMemberException();
        }

        return userchallengeDao.findUserChallengeByUserId(user_id).stream()
                .map(UserChallengeDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserChallenge(UserChallengeDto dto, Long userChallengeId) throws Exception {
        log.info("[UserServiceImpl][updateUserChallenge] Starts");

        if (userchallengeDao.findUserChallengeById(userChallengeId) != null) {

            UserChallengeEntity entity = userchallengeDao.findUserChallengeById(userChallengeId);

            log.info("[UserServiceImpl][updateUserChallenge] entity " + entity);

            userchallengeDao.updateUserChallenge(null);
        }

    }

    @Override
    public void insertUserChallenge(UserChallengeDto dto) throws Exception {
        log.info("[UserServiceImpl][insertUserChallenge] Starts");

        Long user_id = (Long) httpSession.getAttribute("userId");
        if (user_id != null) {
            UserChallengeEntity entity = new UserChallengeEntity();
            dto.setUser_id(user_id);
            EntityUtil.copyNonNullProperties(dto, entity);
            userchallengeDao.insertUserChallenge(entity);
        } else {
            throw new NotFoundMemberException();
        }

    }

    @Override
    public UserCertificateDto getUserCertificate(Long userCertificateId) throws Exception {

        UserCertificateEntity entity = userCertificateDao.findUserCertificateById(userCertificateId);
        return UserCertificateDto.from(entity);
    }

    @Override
    public UserChallengeDto getUserChalleng(Long userChallengeId) throws Exception {
        UserChallengeEntity entity = userchallengeDao.findUserChallengeById(userChallengeId);
        return UserChallengeDto.from(entity);
    }

    @Override
    public UserCertificateDto findUserCertificateId(Long certificateId, Long userId) throws Exception {

        Optional<UserEntity> userEntity = userDao.findById(userId);
        Optional<CertificateInfoEntity> certificateInfoEntity = certificateInfoDao.findById(certificateId);

        if (userEntity.isPresent() && certificateInfoEntity.isPresent()) {
            UserCertificateEntity userCertificateEntity = userCertificateDao
                    .findUserCertificateId(certificateInfoEntity.get(), userEntity.get());
            return UserCertificateDto.from(userCertificateEntity);
        } else {
            throw new EntityNotFoundException("UserCertificateEntity Not Found");
        }
    }


    @Override
    public UserChallengeDto findUserChallengeId(Long challengeId, Long userId) throws Exception {

        Optional<UserEntity> userEntity = userDao.findById(userId);
        Optional<ChallengeInfoEntity> challengeEntity = challengeInfoDao.findById(challengeId);

        if (userEntity.isPresent() && challengeEntity.isPresent()) {
            UserCertificateEntity userCertificateEntity = userCertificateDao
                    .findUserCertificateId(certificateInfoEntity.get(), userEntity.get());
            return UserCertificateDto.from(userCertificateEntity);
        } else {
            throw new EntityNotFoundException("UserCertificateEntity Not Found");
        }
    }

    @Override
    public UserChallengeDto findUserChallengeId(Long challengeId, Long userId) throws Exception {
        if (userchallengeDao.findUserChallengeId(challengeId, userId).isEmpty()) {
            throw new Exception("User challenge not found");
        }
        return userchallengeDao.findUserChallengeId(challengeId, userId).get();
    }

}
