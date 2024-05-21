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
@Entity(name = "UserChalllengeEntity")
@Table(name = "userChallenge")
public class UserChalllengeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userChallengeId", nullable = false)
    private Long userChallengeId;

    @Column(name = "progressRate")
    private Double progressRate;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "challengeId", nullable = false)
    private Long challengeId;

    @Column(name = "userId", nullable = false)
    private Long userId;
}
