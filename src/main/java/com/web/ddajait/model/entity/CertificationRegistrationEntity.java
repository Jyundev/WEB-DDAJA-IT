package com.web.ddajait.model.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity(name = "CertificationRegistrationEntity")
@Table(name = "certificateRegistration")
public class CertificationRegistrationEntity {
    @Id
    @Column(name = "registrationID", nullable = false)
    private Long registrationId;

    // @OneToOne
    // @MapsId
    // @JoinColumn(name = "CertificateId")
    // private CertificateInfoEntity certificateInfoEntity;

    @Column(name = "certificateName", nullable = false, length = 255)
    private String certificateName;

    @Column(name = "round", nullable = false)
    private Timestamp round;

    @Column(name = "testDay", nullable = false)
    private Timestamp testDay;

    @Column(name = "receptionStart", nullable = false)
    private Timestamp receptionStart;

    @Column(name = "receptionEnd", nullable = false)
    private Timestamp receptionEnd;

    @Column(name = "resultDay", nullable = false)
    private Timestamp resultDay;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "certificateId", nullable = false)
    private Long certificateId;

    
}