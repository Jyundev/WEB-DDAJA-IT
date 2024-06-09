package com.web.ddajait.model.entity;

import java.util.List;

import com.web.ddajait.util.ListToJsonConverter;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PartQuestionEntity")
@Table(name = "partQuestion")
public class PartQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(nullable = false, length = 255)
    private String certificateName;

    @Column(nullable = false, length = 255)
    private String partName;

    @Column(nullable = false, length = 255)
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes;

    @Convert(converter = ListToJsonConverter.class)
    @Column(nullable = false, columnDefinition = "TEXT")
    private List<String> choices;

    @Column(nullable = false)
    private int answer;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String image;

    @ManyToOne
    @JoinColumn(name = "challenge_part_Id")
    private ChallengePartEntity challengePart;
    
    @ManyToOne
    @JoinColumn(name = "certificate_part_id")
    private CertificatePartEntity certificatePartInfo;

    @OneToMany(mappedBy = "partQuestion", cascade = CascadeType.ALL)
    private List<UserWrongQuestionEntity> wrongQuestions;


}
