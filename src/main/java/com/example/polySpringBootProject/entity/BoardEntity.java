package com.example.polySpringBootProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
//  Memo.builder().fiedl1(value1).field(value2).build();
@Setter
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
@Table(name = "tbl_board")  // 테이블 이름 지정(이 애노테이션 안쓰면 클래스명이 테이블명)
@Entity  // 이 클래스로 DB 생성
public class BoardEntity extends BaseEntity {

    @Id  // 기본키 속성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long num;

    @Column(length = 200, nullable=false)  // 일반 속성
    private String title;

    @Lob  // 일반 속성
    private String content;

    @Column
    private String notice;

    @Column
    private String secret;

    @Column  // 파일첨부 여부(1 : 파일첨부O, 0 : 파일첨부x)
    private int fileAttached;

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    LocalDateTime regDate;
//
//    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    LocalDateTime modDate;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)  // 다 대 일 (다 : 주인, 자식, 일 : 주인X, 부모)
    @JoinColumn(name="member_id")  // 연관관계 주인임을 나타냄 , name = 외래키 이름
    private MemberEntity member;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntities = new ArrayList<>();

}
