package com.web.ddajait.model.entity;

import java.util.List;
// https://ict-nroo.tistory.com/132
// https://resilient-923.tistory.com/417

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CertificateInfoEntity")
@Table(name = "certificateInfo")
public class CertificateInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id", nullable = false)
    private Long certificate_id;

    @Column(nullable = false, length = 100)
    private String certificateName;

    @Column(nullable = false, length = 255)
    private String certificateFullName;

    @Column(nullable = false, length = 100)
    private String field;

    @Column(nullable = false, length = 50)
    private String types;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String overview;

    @Column(nullable = true, length = 50)
    private String difficulty;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String eligibility;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String examContent;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String examStandards;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String passCriteria;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String registrationLink;

    @Column(columnDefinition = "TEXT")
    private String relatedJob;

    @OneToMany(mappedBy = "certificateInfo", cascade = CascadeType.ALL)
    private List<CertificationRegistrationEntity> certificationRegistrations;

    @OneToMany(mappedBy = "certificateInfo", cascade = CascadeType.ALL)
    private List<UserCertificateEntity> userCertifications;

    @OneToMany(mappedBy = "certificateInfo", cascade = CascadeType.ALL)
    private List<ChallengeInfoEntity> challenges;

    
}