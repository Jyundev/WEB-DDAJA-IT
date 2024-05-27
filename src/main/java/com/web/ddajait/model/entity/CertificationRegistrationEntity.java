package com.web.ddajait.model.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "CertificationRegistrationEntity")
@Table(name = "certificateRegistration")
public class CertificationRegistrationEntity {
    @Id
    @Column(nullable = false)
    private Long registrationId;

    @Column(nullable = false, length = 255)
    private String certificateName;

    @Column(nullable = false, length = 50)
    private String types;

    @Column(nullable = false)
    private int round;

    @Column(nullable = false)
    private Timestamp testDay;

    @Column(nullable = false)
    private Timestamp receptionStart;

    @Column(nullable = false)
    private Timestamp receptionEnd;

    @Column(nullable = false)
    private Timestamp resultDay;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private CertificateInfoEntity certificateInfo;


}