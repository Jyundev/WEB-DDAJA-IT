package com.web.ddajait.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ChallengeInfoEntity")
@Table(name = "challengeInfo")
public class ChallengeInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challengeId", nullable = false)
    private Long challengeId;

    @Column(name = "challengeName", nullable = false, length = 100)
    private String challengeName;

    @Column(name = "challengeInfo", nullable = false, columnDefinition = "TEXT")
    private String challengeInfo;

    @Column(name = "chapter", nullable = false, length = 255)
    private String chapter;

    @Column(name = "book", nullable = false, length = 100, columnDefinition = "varchar(100) default '이기적'")
    private String book;

    @Column(name = "passRate", nullable = true, length = 10)
    private String passRate;

    @Column(name = "memberPassRate", nullable = true, length = 10)
    private String memberPassRate;

    @Column(name = "totalprogressRate", nullable = true, length = 10)
    private String totalprogressRate;

    @Column(name = "certificateId", nullable = false)
    private Long certificateId;

    // @OneToMany(mappedBy = "challengeInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    // private List<ChallengeChapterEntity> challengeChapterList;
}
