package com.example.polySpringBootProject.entity;

import com.example.polySpringBootProject.enumClass.DeliveryType;
import com.example.polySpringBootProject.enumClass.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
//  Memo.builder().fiedl1(value1).field(value2).build();
@Setter  // Setter 메서드 사용가능
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
@Table(name = "tbl_order")  // 테이블 이름 지정(이 애노테이션 안쓰면 클래스명이 테이블명)
@Entity  // 이 클래스로 DB 생성
public class OrderEntity extends BaseEntity{

    @Id  // 기본키 속성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long num;  // 기본키 번호

    @Column
    private long orderUniqueNumber;

    @Column
    private String submitPost;

    @Column
    private String submitAddr;

    @Column
    private String submitAddrDetail;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column
    private String submitOrderMessageChoice;

    @Column
    private int submitUseAccumulatedMoney;

    @Column
    private int submitAmount;

    @Column
    private int submitGoodsTotalPrice;

    @Column
    private int submitFinalPrice;

    @Column
    private int submitAccumulatedMoney;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="goodsNum")
    private GoodsEntity goods;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberNum")
    private MemberEntity member;
}