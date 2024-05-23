package com.web.ddajait.model.entity;

import java.util.List;

import com.web.ddajait.util.JsonListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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

    @Column(name = "missionDay", nullable = false, length = 50)
    private String missionDay;

    @Column(name = "chapterNum", nullable = false)
    private int chapterNum;

    @Column(name = "chapterName", nullable = false, length = 255)
    private String chapterName;

    @Column(name = "chapterLink")
    @Convert(converter = JsonListConverter.class)
    private List<String> chapterLink;

    @Column(name = "chapterMission", nullable = true, length = 255)
    private String chapterMission;

    @Column(name = "challengeId", nullable = false)
    private String challengeId;

    // @ManyToOne
    // @JoinColumn(name = "ChallengeId")
    // private ChallengeInfoEntity challenges; // FK

}
