package com.web.ddajait.model.entity;

import java.sql.Timestamp;

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
@Entity(name = "ChallengeChapterEntity")
@Table(name = "challengeChapter")
public class ChallengeChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapterId", nullable = false)
    private Long chapterId;

    @Column(name = "chapterNum", nullable = false)
    private int chapterNum;

    @Column(name = "chapterName", nullable = false, length = 255)
    private String chapterName;

    @Column(name = "missionDay", nullable = false)
    private Timestamp missionDay;

    @Column(name = "chapterLink", length = 1000)
    private String chapterLink;

    @Column(name = "chapterMission", nullable = false, length = 255)
    private String chapterMission;

    // @ManyToOne
    // @JoinColumn(name = "ChallengeId")
    // private ChallengeInfoEntity challenges; // FK


    

}
