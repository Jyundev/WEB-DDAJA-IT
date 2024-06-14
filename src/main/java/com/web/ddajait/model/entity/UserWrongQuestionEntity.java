package com.web.ddajait.model.entity;

import java.util.List;

import com.web.ddajait.util.ListToJsonConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserWrongQuestionEntity")
@Table(name = "userWrongQuestion")
public class UserWrongQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long wrongId;

    @Convert(converter = ListToJsonConverter.class)
    @Column(nullable = false, columnDefinition = "TEXT")
    private List<Integer> wrongQuestions;

    // @ManyToOne
    // @JoinColumn(name = "question_id", nullable = false)
    // private PartQuestionEntity partQuestion;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private ChallengeInfoEntity challengeInfo;

}
