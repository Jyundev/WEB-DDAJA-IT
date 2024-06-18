package com.web.ddajait.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
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
import com.web.ddajait.model.dto.User.UserCertificateDetailDto;
import com.web.ddajait.model.dto.User.UserCertificateDto;
import com.web.ddajait.model.dto.User.UserDto;
import com.web.ddajait.model.dto.User.UserPrivateInfoDto;
import com.web.ddajait.model.dto.User.UserChallenge.UserChallengeApiDto;
import com.web.ddajait.model.dto.User.UserChallenge.UserChallengeDto;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    static List<String> strToList(String data) {
        List<String> strArray;

        if (data.contains(",")) {
            String[] dataArray = data.split(", ");
            strArray = Arrays.asList(dataArray);
        } else {
            strArray = Arrays.asList(data);
        }

        return strArray;
    }

    private final UserDao userDao;
    private final ChallengeInfoDao challengeInfoDao;
    private final CertificateInfoDao certificateInfoDao;
    private final UserCertificateDao userCertificateDao;

    private final UserchallengeDao userchallengeDao;
    private final PasswordEncoder bCryptPasswordEncoder;

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
            userDao.deleteUser(entity.get().getUserId());

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
        log.info("[UserServiceImpl][createMember] userDto : " + userDto);

        if (userDto.getNickname().equals(Role.ADMIN.name())) {

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

        userDao.createMember(userEntity);

    }

    // 프로필 수정
    @Override
    @Transactional
    public void updateUser(UserPrivateInfoDto userDto, Long userId) throws Exception {
        log.info("[UserServiceImpl][updateUser] Start");

        // 기존 사용자 정보 가져오기
        if (userDao.findById(userId) != null) {

            Optional<UserEntity> userEntityOptional = userDao.findById(userId);

            if (userEntityOptional.isPresent()) {

                UserEntity entity = userEntityOptional.get();
                log.info("[UserServiceImpl][updateUser] entity " + entity.getNickname());

                if (userDto.getProfileImage().length() == 0) {
                    userDto.setProfileImage(entity.getProfileImage());
                }
                if (userDto.getNickname().length() == 0) {
                    userDto.setNickname(entity.getNickname());
                }

                // 중복회원 처리
                int nicknameCheck = countMemberByMemberNickname(userDto.getNickname());

                // 닉네임 중복
                if (nicknameCheck > 0 && !entity.getNickname().equals(userDto.getNickname())) {
                    throw new DuplicateMemberException(userDto.getNickname());
                }

                // userDto 속성중 Null값이 아닌 값만 userEntity로 복사
                EntityUtil.copyNonNullProperties(userDto, entity);
                userDao.updateUser(entity);
            }

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

    /* 유저 자격증 */

    @Override
    public UserDto getUserWithAuthorities(String username) throws Exception {
        log.info("[UserServiceImpl][getUserWithAuthorities] Start ");
        return UserDto.from(userDao.getUserWithAuthorities(username));

    }

    @Override
    public List<UserCertificateDetailDto> getUserCertificateList(Long userId) throws Exception {
        log.info("[UserServiceImpl][getUserCertificate] Starts");

        if (userId == null) {
            throw new NotFoundMemberException();
        }

        return userCertificateDao.findUserCertificateByUserId(userId).stream()
                .map(UserCertificateDetailDto::from)
                .collect(Collectors.toList());

    }

    @Override
    public void updateUserCertificate(UserCertificateDto dto, Long certificateId, Long userId) throws Exception {
        log.info("[UserServiceImpl][updateUserCertificate] Starts");
        // Long userId = (Long) httpSession.getAttribute("userId");

        if (userCertificateDao.findByUserIdCertificateId(userId, certificateId) != null) {
            UserCertificateEntity entity = userCertificateDao.findByUserIdCertificateId(userId, certificateId).get();
            EntityUtil.copyNonNullProperties(dto, entity);
            userCertificateDao.updateUserrCertificate(entity);
        } else {
            throw new NotFoundMemberException();
        }

    }

    /* 유저 챌린지 */

    @Override
    public void inserteUserCertificate(UserCertificateDto dto, Long userId, Long certificateId) throws Exception {
        log.info("[UserServiceImpl][inserteUserCertificate] Starts");

        UserCertificateEntity entity = new UserCertificateEntity();

        if (userId != null) {
            EntityUtil.copyNonNullProperties(dto, entity);
            entity.setUser(userDao.findById(userId).get());
            entity.setCertificateInfo(certificateInfoDao.findById(certificateId).get());
            userCertificateDao.insertUserrCertificate(entity);
        } else {
            throw new NotFoundMemberException();
        }

    }

    @Override
    public List<UserChallengeApiDto> getUserChallengList(Long uerId) throws Exception {
        log.info("[UserServiceImpl][getUserChalleng] Starts");

        return userchallengeDao.findUserChallengeByUserId(uerId).stream()
                .map(entity -> {
                    UserChallengeApiDto userChallengeApiDto = new UserChallengeApiDto();
                    userChallengeApiDto.setChallengeId(entity.getChallengeInfo().getChallengeId());
                    userChallengeApiDto.setChallengeName(entity.getChallengeInfo().getThumbnail());
                    userChallengeApiDto.setChallengeName(entity.getChallengeInfo().getChallengeName());
                    userChallengeApiDto.setProgress(entity.getProgressRate());
                    return userChallengeApiDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserChallenge(UserChallengeDto dto, Long challengeID, Long userId) throws Exception {
        log.info("[UserServiceImpl][updateUserChallenge] Starts");

        Optional<UserChallengeEntity> userChallengeEntity = userchallengeDao.findByUserIdChallengeId(userId,
                challengeID);

        if (userChallengeEntity.isPresent()) {

            UserChallengeEntity entity = userChallengeEntity.get();

            ChallengeInfoEntity challengeInfoEntity = entity.getChallengeInfo();
            Timestamp starTimestamp = challengeInfoEntity.getStartDay();
            Timestamp endTimestamp = challengeInfoEntity.getEndDay();

            long durationInMillis = endTimestamp.getTime() - starTimestamp.getTime();

            // 결과를 일 단위로 변환합니다.
            int period = (int) (durationInMillis / (1000 * 60 * 60 * 24)) + 1;

            // 전체기간 나누기 현재 유저가 진행한 날

            dto.setProgressRate(dto.getDay() * 100 / period);

            EntityUtil.copyNonNullProperties(dto, entity);
            userchallengeDao.updateUserChallenge(entity);
        } else {

            Optional<UserEntity> userEntity = userDao.findById(userId);
            if (userEntity.isPresent()) {

                // 챌린지 신청시 권한을 챌린저로 변경
                UserEntity uEntity = userEntity.get();
                // 사용자의 권한 목록에서 해당 챌린지의 챌린저 권한 여부 확인
                boolean hasChallengerRole = uEntity.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthorityName()
                                .equals(Role.CHALLENGER.getKey()));

                // 해당 챌린지에 챌린저 권한이 없는 경우 추가
                if (!hasChallengerRole) {
                    Set<AuthorityEntity> authorities = uEntity.getAuthorities();

                    // 새로운 챌린지 챌린저 권한 추가
                    authorities.add(AuthorityEntity.builder()
                            .authorityName(Role.CHALLENGER.getKey())
                            .build());

                    // 권한 목록 업데이트
                    uEntity.setAuthorities(authorities);

                    userDao.updateUser(uEntity);
                }

            }

            UserChallengeEntity entity = new UserChallengeEntity();
            EntityUtil.copyNonNullProperties(dto, entity);
            entity.setUser(userDao.findById(userId).get());
            entity.setChallengeInfo(challengeInfoDao.findById(challengeID).get());
            userchallengeDao.insertUserChallenge(entity);
        }

    }

    @Override
    public void insertUserChallenge(UserChallengeDto dto, Long userId, Long challengeId) throws Exception {
        log.info("[UserServiceImpl][insertUserChallenge] Starts");

        if (userId != null) {
            UserChallengeEntity entity = new UserChallengeEntity();
            EntityUtil.copyNonNullProperties(dto, entity);
            entity.setUser(userDao.findById(userId).get());
            entity.setChallengeInfo(challengeInfoDao.findById(challengeId).get());
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
    public Optional<UserCertificateDto> findUserCertificateId(Long certificateId, Long userId) throws Exception {
        Optional<UserCertificateEntity> userCertificateEntity = userCertificateDao.findByUserIdCertificateId(userId,
                certificateId);
        if (userCertificateEntity.isPresent()) {
            return userCertificateEntity.map(UserCertificateDto::from);

        } else {
            log.error("Error finding UserCertificateEntity for userId: {} and certificateId: {}", userId, certificateId);

            return Optional.of(new UserCertificateDto());
        }
    }

    @Override
    public UserChallengeDto findByUserIdChallengeId(Long challengeId, Long userId) throws Exception {

        Optional<UserChallengeEntity> userChallengeEntity = userchallengeDao.findByUserIdChallengeId(userId,
                challengeId);
        if (userChallengeEntity.isPresent()) {
            return UserChallengeDto.from(userChallengeEntity.get());

        } else {
            throw new EntityNotFoundException("userChallengeEntity Not Found");
        }

    }

    @Override
    public Long getUserId(String email) throws Exception {
        return userDao.findByEmail(email).getUserId();
    }

    @Override
    public void addUserInfo(Long userId, UserPrivateInfoDto dto) throws Exception {
        log.info("[UserServiceImpl][getAddUserInfo] Starts");

        Optional<UserEntity> entity = userDao.findById(userId);
        if (entity.isPresent()) {
            UserEntity userEntity = entity.get();

            if (dto.getNickname().length() == 0) {
                dto.setNickname(userEntity.getNickname());
            }

            if (dto.getProfileImage().length() == 0) {
                dto.setProfileImage(userEntity.getProfileImage());
            }

            EntityUtil.copyNonNullProperties(dto, userEntity);

            log.info("[UserServiceImpl][getAddUserInfo]  UserInfo : {}, {}", userEntity.getNickname(), dto.getAge());

            userDao.updateUser(userEntity);

            // 자격증 정보 업데이트
            if (!dto.getQualifiedCertificate().isEmpty()) {
                dto.getQualifiedCertificate().forEach(data -> {
                    UserCertificateEntity userCertificateEntity = new UserCertificateEntity();

                    try {
                        // 자격증 정보 찾기
                        Optional<CertificateInfoEntity> certificateInfoOpt = certificateInfoDao
                                .findByCertificateName(data);
                        if (certificateInfoOpt.isPresent()) {
                            userCertificateEntity.setCertificateInfo(certificateInfoOpt.get());
                            userCertificateEntity.setUserResult(true);
                            userCertificateEntity.setApplication(true);

                            // 사용자 정보 찾기
                            Optional<UserEntity> userOpt = userDao.findById(userId);
                            if (userOpt.isPresent()) {
                                userCertificateEntity.setUser(userOpt.get());

                                // UserCertificateEntity 삽입
                                userCertificateDao.insertUserrCertificate(userCertificateEntity);

                            } else {
                                // 사용자 정보가 없을 때 예외 처리
                                System.err.println("User with ID " + userId + " not found.");
                            }
                        } else {
                            // 자격증 정보가 없을 때 예외 처리
                            System.err.println("Certificate with name " + data + " not found.");
                        }
                    } catch (Exception e) {
                        // 일반적인 예외 처리
                        System.err.println(
                                "An error occurred while processing certificate " + data + " for user ID " + userId);
                        e.printStackTrace();
                    }
                });

            } else {
                throw new NotFoundMemberException("User with id " + userId + " not found");
            }
        }
    }

    @Override
    public int countMemberByChallengeId(Long challengeId) {
        return userchallengeDao.countMemberByChallengeId(challengeId);
    }

}
