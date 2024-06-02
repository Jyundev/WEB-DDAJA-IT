package com.web.ddajait.model.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserEntity")
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long user_id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column( nullable = false, length = 50)
    @Size(min = 2, max = 10, message = "닉네임은 최소 2자에서 최대 10자여야 합니다.")
    private String nickname;

    @Column( nullable = false, length = 100)
    private String password;

    @Column()
    private int age;

    @Column( length = 50)
    private String gender;

    @Builder.Default
    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin = false;

    @Column( columnDefinition = "TEXT")
    private String interest;

    @Column( length = 100)
    private String job;

    @Column( length = 255)
    private String profileImage;

    @Column()
    private int tier;

    @Column(columnDefinition = "TEXT")
    private String qualifiedCertificate;

    // 일반사용자 / 관리자를 구분용
    @Column( length = 50)
    private String role;

    // UserEntity와 AuthorityEntity 간의 다대다 관계를 정의하고, 
    // 그 관계를 관리하는 user_authority 테이블을 생성
    @ManyToMany
    @JoinTable(name = "user_authority", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "authority_name", referencedColumnName = "authority_name") })
    private Set<AuthorityEntity> authorities;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserChalllengeEntity> userChallenges;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCertificateEntity> userCertifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserWrongQuestionEntity> userWrongQuestions;



}
