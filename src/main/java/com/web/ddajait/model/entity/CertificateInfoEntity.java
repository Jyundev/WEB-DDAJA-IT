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
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CertificateInfoEntity")
@Table(name = "certificateInfo")
public class CertificateInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificateId", nullable = false)
    private Long certificateId;

    @Column(nullable = false, length = 100)
    private String certificateName;

    @Column(nullable = false, length = 255)
    private String certificateFullName;

    @Column(nullable = false, length = 100)
    private String field;

    @Column(nullable = false, length = 50)
    private String types;
  
    @Column(nullable = true, columnDefinition = "TEXT")
    private String thumbnail;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String overview;

    @Column(nullable = true, length = 50)
    private String difficulty;

    @Lob
    @Column(nullable = false, columnDefinition = "json")
    private String eligibility;

    @Lob
    @Column(nullable = false, columnDefinition = "json")
    private String examContent;

    @Lob
    @Column(nullable = false, columnDefinition = "json")
    private String examStandards;

    @Lob
    @Column(nullable = false, columnDefinition = "json")
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

    @OneToMany(mappedBy = "certificateInfo", cascade = CascadeType.ALL)
    private List<PartQuestionEntity> partQuestionEntities;

    @OneToMany(mappedBy = "certificateInfo", cascade = CascadeType.ALL)
    private List<ChallengePartEntity> challengePartEntities;
    
}