package com.example.polySpringBootProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)  // 수정시에는 변화X
    private LocalDateTime createdTime;  // 생성시간

    @UpdateTimestamp
    @Column(insertable = false)  // 생성시에는 변환X
    private LocalDateTime updatedTime;  // 수정시간
}
