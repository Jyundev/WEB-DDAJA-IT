package com.web.ddajait.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.CertificateInfoEntity;
import com.web.ddajait.model.entity.UserCertificateEntity;
import com.web.ddajait.model.entity.UserEntity;

public interface UserCertificateRepository extends JpaRepository<UserCertificateEntity, Long> {

    public List<UserCertificateEntity> findByUser_Id(Long user_id);

    // @Query(value = "SELECT user_certificate_id FROM user_certificate WHERE
    // user_id = :user_id AND certificate_id = :certificate_id", nativeQuery = true)
    // Optional<Long> findUserCertificateByUserIdAndCertificateId(@Param("user_id")
    // Long userId, @Param("certificate_id") Long certificateId);

    Optional<UserCertificateEntity> findByUserAndCertificateInfo(UserEntity user,
            CertificateInfoEntity certificateInfo);

}
