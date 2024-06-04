package com.web.ddajait.model.entity;

import java.util.List;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ChallengeChapterEntity")
@Table(name = "challengeChapter")
public class ChallengeChapterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long chapter_id;

    @Column(nullable = false)
    private int missionDay;

    @Column(nullable = false)
    private int startDay;

    @Column(nullable = false)
    private int endDay;

    @Column(nullable = false)
    private int chapterNum;

    @Column( nullable = false, length = 255)
    private String chapterName;

    @Column( columnDefinition = "TEXT")
    private String chapterLink;

    @Column( nullable = true, length = 255)
    private String chapterMission;


    @OneToMany(mappedBy = "challengeChapter")
    private List<ChapterQuestionEntity> questions;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private ChallengeInfoEntity challengeInfo;



}
