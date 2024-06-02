package com.web.ddajait.model.entity;

import java.util.List;

import com.web.ddajait.util.JsonListConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ChapterQuestionEntity")
@Table(name = "chapterQuestion")
public class ChapterQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(nullable = false, length = 255)
    private String chapter;

    @Column(nullable = false, length = 255)
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Convert(converter = JsonListConverter.class)
    private String choices;

    @Column(nullable = false)
    private int answer;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String image;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private ChallengeChapterEntity challengeChapter;

    @OneToMany(mappedBy = "chapterQuestion", cascade = CascadeType.ALL)
    private List<UserWrongQuestionEntity> wrongQuestions;


}
