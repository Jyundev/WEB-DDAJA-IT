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
@Entity(name = "IncorrectAnswerEntity")
@Table(name = "incorrectAnswer")
public class IncorrectAnswerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incorrectId", nullable = false)
    private Long incorrectId;

    @Column(name = "examId", nullable = false)
    private Long examId;

    @Column(name = "userId", nullable = false)
    private Long userId;
}
