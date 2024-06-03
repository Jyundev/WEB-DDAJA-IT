package com.web.ddajait.model.dao;

import java.util.List;
import java.util.Optional;

import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.model.entity.UserCertificateEntity;
import com.web.ddajait.model.entity.UserEntity;

public interface UserCertificateDao {

    /**
     * UserCertificate 테이블에서 특정 유저의 자격증 정보를 가져온다.
     * 
     * @return 특정 유저의 자격증 정보
     */
    public List<UserCertificateEntity> findUserCertificateByUserId(Long userId);

    public UserCertificateEntity findUserCertificateById(Long id);

    // 데이터 찾기
    public UserCertificateEntity findUserCertificateId(CertificateInfoEntity certificateInfoEntity, UserEntity userEntity);

    // 데이터 업데이트
    public void updateUserrCertificate(UserCertificateEntity entity);

    // 데이터 추가
    public void insertUserrCertificate(UserCertificateEntity entity);

}
