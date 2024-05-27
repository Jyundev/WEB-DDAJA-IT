package com.web.ddajait.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "UserWrongQuestionEntity")
@Table(name = "userWrongQuestion")
public class UserWrongQuestionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long incorrectId;

    @ManyToOne
    @JoinColumn(name = "question_id",  nullable = false)
    private ChapterQuestionEntity chapterQuestion;

    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private UserEntity user;
}
