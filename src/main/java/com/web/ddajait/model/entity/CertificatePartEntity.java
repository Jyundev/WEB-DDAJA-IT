package com.web.ddajait.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

import java.util.List;

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
    private Long certificate_part_id;

    @NotBlank
    private String certificateName;

    @NotBlank
    private String certificatePart;
    
    @OneToMany(mappedBy = "certificatePartInfo", cascade = CascadeType.ALL)
    private List<ChallengePartEntity> challengePartEntities;

    @OneToMany(mappedBy = "certificatePartInfo", cascade = CascadeType.ALL)
    private List<PartQuestionEntity> partQuestionEntities;


}
