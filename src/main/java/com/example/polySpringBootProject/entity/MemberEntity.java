package com.example.polySpringBootProject.entity;

import com.example.polySpringBootProject.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
//  Memo.builder().fiedl1(value1).field(value2).build();
@Setter  // Setter 메서드 사용가능
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
@Table(name = "tbl_member")  // 테이블 이름 지정(이 애노테이션 안쓰면 클래스명이 테이블명)
@Entity  // 이 클래스로 DB 생성
public class MemberEntity extends BaseEntity{

    @Id  // 기본키 속성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long num;

    @Column(length = 200, unique = true)  // 일반 속성
    //@NotBlank(message = "password cannot be blank")
    private String id;

    @Column(length = 200, nullable=false)  // 일반 속성
    //@NotBlank(message = "password cannot be blank")
    private String pw;

    @Column(length=40, nullable=false)
    //@NotBlank(message = "name cannot be blank")
    private String name;

    @Column(length=40)
    //@NotBlank(message = "address cannot be blank")
    private String post;

    @Column(length=40)
    //@NotBlank(message = "address cannot be blank")
    private String addr;

    @Column(length=40)
    //@NotBlank(message = "address cannot be blank")
    private String addrDetail;

    @Column
    // @NotBlank(message = "age cannot be blank")  int는 이 애노테이션 불가
    private int age;

    @Column
    private String email;

//    @CreationTimestamp
//    //@Temporal(TemporalType.TIMESTAMP)
//    LocalDateTime reg_date;
//
//    @UpdateTimestamp
//    //@Temporal(TemporalType.TIMESTAMP)
//    LocalDateTime mod_date;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @OneToMany(mappedBy = "member",  // 일 대 다 (다 : 주인, 자식, 일 : 주인X, 부모)
            cascade=CascadeType.REMOVE)  //  mappedBy : 연관관계 주인이 아님을 나타냄, 값으로 주인에서 사용하는 외래키 필드명을 씀
    private List<BoardEntity> boardDatas = new ArrayList<>();

}
