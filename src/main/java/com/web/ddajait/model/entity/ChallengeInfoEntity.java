package com.web.ddajait.model.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ChallengeInfoEntity")
@Table(name = "challengeInfo")
public class ChallengeInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long challengeId;

    @Column(nullable = false, length = 100)
    private String challengeName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String challengeDetail;

    @Column(nullable = false, length = 50)
    private String book;

    @Column(nullable = true, length = 10)
    private String passRate;

    @Column(nullable = true, length = 10)
    private String memberPassRate;

    @Column(nullable = true, length = 10)
    private String totalprogressRate;

    @Column(nullable = false, length = 10)
    private Timestamp startDay;

    @Column(nullable = false, length = 10)
    private Timestamp endDay;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "certificateId")
    private CertificateInfoEntity certificateInfo;

    @OneToMany(mappedBy = "challengeInfo", cascade = CascadeType.ALL)
    private List<ChallengePartEntity> challengeParts;

    @OneToMany(mappedBy = "challengeInfo", cascade = CascadeType.ALL)
    private List<UserChallengeEntity> userChallenges;

    @OneToMany(mappedBy = "challengeInfo", cascade = CascadeType.ALL)
    private List<UserWrongQuestionEntity> userWrongQuestions;




}
