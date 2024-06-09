package com.web.ddajait.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ddajait.model.entity.UserCertificateEntity;

public interface UserCertificateRepository extends JpaRepository<UserCertificateEntity, Long> {

    public List<UserCertificateEntity> findByUser_UserId(Long userId);

    // @Query(value = "SELECT user_certificate_id FROM user_certificate WHERE
    // user_id = :user_id AND certificate_id = :certificate_id", nativeQuery = true)
    // Optional<Long> findUserCertificateByUserIdAndCertificateId(@Param("user_id")
    // Long userId, @Param("certificate_id") Long certificateId);

    Optional<UserCertificateEntity> findByUser_UserIdAndCertificateInfo_CertificateId(Long userId,
            Long certificateId);

}
