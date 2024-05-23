package com.web.ddajait.model.entity;

import java.util.List;
// https://ict-nroo.tistory.com/132
// https://resilient-923.tistory.com/417

import com.web.ddajait.util.JsonListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Entity(name = "CertificateInfoEntity")
@Table(name = "certificateInfo")
public class CertificateInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificateId", nullable = false)
    private Long certificateId;

    @Column(name = "certificateName", nullable = false, length = 100)
    private String certificateName;

    @Column(name = "certificateFullName", nullable = false, length = 255)
    private String certificateFullName;

    @Column(name = "field", nullable = false, length = 100)
    private String field;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "overview", nullable = false, columnDefinition = "TEXT")
    private String overview;

    @Column(name = "difficulty", nullable = true, length = 50)
    private String difficulty;

    @Column(name = "eligibility", nullable = false)
    @Convert(converter = JsonListConverter.class)
    private List<String> eligibility;

    @Column(name = "examContent", nullable = false)
    @Convert(converter = JsonListConverter.class)
    private List<String> examContent;

    @Column(name = "examStandards", nullable = false)
    @Convert(converter = JsonListConverter.class)
    private List<String> examStandards;

    @Column(name = "passCriteria", nullable = false)
    @Convert(converter = JsonListConverter.class)
    private List<String> passCriteria;

    @Column(name = "registrationLink", nullable = false)
    private String registrationLink;

    @Column(name = "relatedJob")
    @Convert(converter = JsonListConverter.class)
    private List<String> relatedJob;


    // @OneToOne(mappedBy = "certificateInfo", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY, orphanRemoval = true)
    // private CertificationSchedulenEntity certificationSchedulen;

}