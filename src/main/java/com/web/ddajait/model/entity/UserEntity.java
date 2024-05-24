package com.web.ddajait.model.entity;

import java.util.List;
import java.util.Set;

import com.web.ddajait.util.JsonListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserEntity")
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "nickname", nullable = false, length = 50)
    @Size(min = 2, max = 10, message = "닉네임은 최소 2자에서 최대 10자여야 합니다.")
    private String nickname;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "age")
    private int age;

    @Column(name = "gender", length = 50)
    private String gender;

    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin;

    @Column(name = "interest")
    @Convert(converter = JsonListConverter.class)
    private List<String> interest;

    @Column(name = "job", length = 100)
    private String job;

    @Column(name = "profileImage", length = 255)
    private String profileImage;

    @Column(name = "tier")
    private int tier;

    @Column(name = "qualifiedCertificate", columnDefinition = "TEXT")
    private String qualifiedCertificate;

    // 일반사용자 / 관리자를 구분용
    @Column(name = "role", length = 50)
    private String role;

    // UserEntity와 AuthorityEntity 간의 다대다 관계를 정의하고, 그 관계를 관리하는 user_authority 테이블을
    // 생성
    @ManyToMany
    @JoinTable(name = "user_authority", joinColumns = {
            @JoinColumn(name = "userId", referencedColumnName = "userId") }, inverseJoinColumns = {
                    @JoinColumn(name = "authority_name", referencedColumnName = "authority_name") })
    private Set<AuthorityEntity> authorities;

}
