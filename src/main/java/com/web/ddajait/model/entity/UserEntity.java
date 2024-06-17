package com.web.ddajait.model.entity;

import java.util.List;
import java.util.Set;

import com.web.ddajait.util.ListToJsonConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 50)
    @Size(min = 2, max = 10, message = "닉네임은 최소 2자에서 최대 10자여야 합니다.")
    private String nickname;

    @Column(nullable = false, length = 100)
    private String password;

    @Column()
    private String age;

    @Column(length = 50)
    private String gender;

    @Builder.Default
    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin = false;

    @Convert(converter = ListToJsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> interest;

    @Column(length = 50)
    private String job;

    @Builder.Default
    @Column(columnDefinition = "TEXT")
    private String profileImage = "https://ddajait-db-s3.s3.ap-northeast-2.amazonaws.com/image/profile/default_profile.png";

    private int tier;

    @Convert(converter = ListToJsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> qualifiedCertificate;

    @Transient
    @Convert(converter = ListToJsonConverter.class)
    private List<String> qualifiedCertificateList;

    // UserEntity와 AuthorityEntity 간의 다대다 관계를 정의하고,
    // 그 관계를 관리하는 user_authority 테이블을 생성
    @ManyToMany
    @JoinTable(name = "user_authority", joinColumns = {
            @JoinColumn(name = "userId", referencedColumnName = "userId") }, inverseJoinColumns = {
                    @JoinColumn(name = "authority_name", referencedColumnName = "authority_name") })
    private Set<AuthorityEntity> authorities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserChallengeEntity> userChallenges;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserCertificateEntity> userCertifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserWrongQuestionEntity> userWrongQuestions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemoEntity> memoEntities;

}
