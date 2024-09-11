package com.example.polySpringBootProject.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
@Setter
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
@Table(name = "tbl_cartItem")  // 테이블 이름 지정(이 애노테이션 안쓰면 클래스명이 테이블명)
@Entity  // 이 클래스로 DB 생성
public class CartItemEntity extends BaseEntity{

    @Id  // 기본키 속성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long num;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartNum")
    private CartEntity cartEntity;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GoodsNum")
    private GoodsEntity goods;

    @Column
    private int goodsAmount;

    public static CartItemEntity createCartItem(CartEntity cart, GoodsEntity goods, int goodsAmount){
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCartEntity(cart);
        cartItemEntity.setGoods(goods);
        cartItemEntity.setGoodsAmount(goodsAmount);
        return cartItemEntity;
    }

    public void addGoodsAmount(int goodsAmount){  // this를 사용하려면 static 사용 불가
        System.out.println("원래 수량 : "+this.goodsAmount);
        System.out.println("추가할 수량 : "+goodsAmount);
        this.goodsAmount += goodsAmount;
    }
}