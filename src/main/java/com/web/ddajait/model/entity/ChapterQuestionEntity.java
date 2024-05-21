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
@Entity(name = "ChapterQuestionEntity")
@Table(name = "chapterQuestion")
public class ChapterQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examId", nullable = false)
    private Long examId;

    @Column(name = "chapterId", nullable = false)
    private Long chapterId;

    @Column(name = "chapter", nullable = false, length = 255)
    private String chapter;

    @Column(name = "question", nullable = false, length = 255)
    private String question;

    @Column(name = "notes", nullable = false, columnDefinition = "TEXT")
    private String notes;

    @Column(name = "choices", nullable = false, length = 1000)
    private String choices;

    @Column(name = "answer", nullable = false)
    private int answer;

    @Column(name = "image", length = 255)
    private String image;
}
