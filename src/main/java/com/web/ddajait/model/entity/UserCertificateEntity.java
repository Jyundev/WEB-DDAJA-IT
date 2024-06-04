package com.web.ddajait.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserCertificateEntity")
@Table(name = "userCertificate")
public class UserCertificateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long userCetificateId;

    @Column( nullable = false)
    private Boolean application;

    @Column(nullable = true)
    private Boolean userResult;

    @ManyToOne
    @JoinColumn(name = "userId",  nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "certificateId")
    private CertificateInfoEntity certificateInfo;
}
