package com.web.ddajait.model.entity;

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
    private Long challengePartId;

    @Column(nullable = false)
    private int day;

    @Column(nullable = false)
    private int partNum;

    @Column( columnDefinition = "TEXT")
    private String partName;

    @Column( columnDefinition = "TEXT")
    private String chapterName;
    
    @Column( columnDefinition = "TEXT")
    private String sectionName;

    @Column( columnDefinition = "TEXT")
    private String partLink;

    @Column( columnDefinition = "TEXT")
    private String memo;

    private boolean randomQuestion;


    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private CertificateInfoEntity certificateInfo;

    @ManyToOne
    @JoinColumn(name = "certificate_part_id")
    private CertificatePartEntity certificatePartInfo;


}
