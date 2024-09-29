package com.web.ddajait.config;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/*
 * 생성 시간과 수정 시간에 자동생성 컬럼 
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "created_date", nullable = false, updatable = false)
    @NotNull
    @CreatedDate
    private LocalDateTime createdDate;

    // @Column(name = "modified_date")
    // @LastModifiedDate
    // private LocalDateTime modifiedDate;

    // 데이터베이스에 '0000-00-00 00:00:00'과 같은 잘못된 값이 입력되는 것을 방지
    public BaseEntity() {
        this.createdDate = LocalDateTime.now();
    }
}
