package com.example.polySpringBootProject.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
//  Memo.builder().fiedl1(value1).field(value2).build();
@Setter
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
@Table(name = "board_file_table")  // 테이블 이름 지정(이 애노테이션 안쓰면 클래스명이 테이블명)
@Entity  // 이 클래스로 DB 생성
public class BoardFileEntity {
    @Id  // 기본키 속성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long num;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)  // EAGER는 자식을 사용하지 않아도 다 가져옴 LAZY는 필요할 때만 가져옴
    @JoinColumn(name = "board_id")  // 외래키 이름
    private BoardEntity boardEntity;
}
