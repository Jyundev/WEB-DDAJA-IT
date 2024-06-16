package com.web.ddajait.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "CertificatePartEntity")
@Table(name = "certificatePart")
public class CertificatePartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificatePartId;

    @NotBlank
    private String certificateName;


    @NotBlank
    private String certificatePart;
    
    @OneToMany(mappedBy = "certificatePartInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChallengePartEntity> challengePartEntities;

    @OneToMany(mappedBy = "certificatePartInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PartQuestionEntity> partQuestionEntities;


}
