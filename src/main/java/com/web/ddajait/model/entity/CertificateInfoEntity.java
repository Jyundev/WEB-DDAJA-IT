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

// https://ict-nroo.tistory.com/132
// https://resilient-923.tistory.com/417

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CertificateInfoEntity")
@Table(name = "certificateInfo")
public class CertificateInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CertificateId", nullable = false)
    private Long certificateId;

    @Column(name = "certificateName", nullable = false, length = 100)
    private String certificateName;

    @Column(name = "certificateFullName", nullable = false, length = 255)
    private String certificateFullName;

    @Column(name = "field", nullable = false, length = 100)
    private String field;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "relatedJob", length = 100)
    private String relatedJob;

    @Column(name = "difficulty", length = 50)
    private String difficulty;

    @Column(name = "registrationFee", length = 50)
    private String registrationFee;

    @Column(name = "certificateInfo", nullable = false, columnDefinition = "TEXT")
    private String certificateInfo;

    @Column(name = "detailInfo", nullable = false, length = 255)
    private String detailInfo;

    @Column(name = "examTendency", nullable = false, length = 255)
    private String examTendency;

    @Column(name = "qualifications", nullable = false, length = 255)
    private String qualifications;

    @Column(name = "passCriteria", nullable = false, length = 255)
    private String passCriteria;

    // @OneToOne(mappedBy = "certificateInfo", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY, orphanRemoval = true)
    // private CertificationSchedulenEntity certificationSchedulen;

}