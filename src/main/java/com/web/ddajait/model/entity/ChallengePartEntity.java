package com.web.ddajait.model.entity;

import java.sql.Timestamp;
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
@Entity(name = "ChallengePartEntity")
@Table(name = "challengePart")
public class ChallengePartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long partId;

    @Column(nullable = false)
    private Timestamp missionDay;

    @Column(nullable = false)
    private Timestamp startDay;

    @Column(nullable = false)
    private Timestamp endDay;

    @Column(nullable = false)
    private int partNum;

    @Column( columnDefinition = "TEXT")
    private String partName;

    @Column( columnDefinition = "TEXT")
    private String chapterName;
    
    @Column( columnDefinition = "TEXT")
    private String sessionName;

    @Column( columnDefinition = "TEXT")
    private String partLink;

    @Column( columnDefinition = "TEXT")
    private String partMission;

    @Column( columnDefinition = "TEXT")
    private String memo;

    @OneToMany(mappedBy = "challengePart")
    private List<PartQuestionEntity> questions;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private ChallengeInfoEntity challengeInfo;



}
