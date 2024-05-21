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
@Entity(name = "UserCertificateEntity")
@Table(name = "userCertificate")
public class UserCertificateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userCetificateId", nullable = false)
    private Long userCetificateId;

    @Column(name = "application", nullable = false)
    private Boolean application;

    @Column(name = "userResult", nullable = false)
    private Boolean userResult;

    @Column(name = "certificateId", nullable = false)
    private Long certificateId;

    @Column(name = "userId", nullable = false)
    private Long userId;
}
