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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserchallengeEntity")
@Table(name = "userChallenge")
public class UserChallengeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userChallenge_id;

    @Column(length = 10)
    private String progressRate;

    @Column( nullable = false, length = 50)
    private String challengeStep;

    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private ChallengeInfoEntity challengeInfo;
}
