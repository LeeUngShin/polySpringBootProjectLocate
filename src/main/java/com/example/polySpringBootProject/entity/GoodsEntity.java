package com.example.polySpringBootProject.entity;

import com.example.polySpringBootProject.MyConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
//  Memo.builder().fiedl1(value1).field(value2).build();
@Setter
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
@Table(name = "tbl_goods")  // 테이블 이름 지정(이 애노테이션 안쓰면 클래스명이 테이블명)
@Entity  // 이 클래스로 DB 생성
public class GoodsEntity extends BaseEntity{
    @Id  // 기본키 속성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long num;  // 상품번호

    @Column(length = 200, nullable = false, unique = true)  // 일반 속성
    private String name;  // 상품이름

    @Column
    private int price;  // 상품가격

    @Column
    private int stock;  // 상품수량

    @Lob  // 일반 속성
    @Column(columnDefinition = "TEXT")
    private String explanation;  // 상품설명

    @Column
    private String del;  // 상품삭제 여부

    @Column
    private int kcal;  // 칼로리

    @Column
    private int protein;  // 단백질

    @Column
    private int fat;  // 지방

    @Column
    private int natrium;  // 나트륨

    @Column
    private int sugar;  // 당류
    
    @Column
    private int weight;  // 중량

    @Column
    private int sellCnt;  // 판매횟수

    @Column
    private int likeCnt;  // 좋아요 개수

    @Lob  // Ensure @Lob is used for large data
    @Column(columnDefinition = "LONGTEXT")  // Use LONGTEXT to ensure sufficient space
    @Convert(converter = MyConverter.class)
    private List<String> allergy;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)  // 다 대 일 (다 : 주인, 자식, 일 : 주인X, 부모)
    @JoinColumn(name = "goodsSubCategoryNum")
    private GoodsSubCategoryEntity goodsSubCategory;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)  // 다 대 일 (다 : 주인, 자식, 일 : 주인X, 부모)
    @JoinColumn(name = "goodsCategoryNum")
    private GoodsCategoryEntity goodsCategory;

    @ToString.Exclude
    @OneToMany(mappedBy = "goodsEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GoodsImageEntity> goodsImageEntity = new ArrayList<>();

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<LikeEntity> likeEntitySet = new HashSet<>();  // 중복 방지를 위해 Set 사용

    public void addLike(LikeEntity likeEntity){
        if (likeEntitySet == null) {
            likeEntitySet = new HashSet<>(); // Ensure initialization if not done already
        }
        likeEntitySet.add(likeEntity);
    }
}

