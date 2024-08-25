package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.entity.MemberEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDto {

    private Long num;

    private String goodsName;

    private int price;

    private int stock;

    private String goodsExplanation;

    private int sellCnt; // 판매횟수

    private String goodsCategory;

    private String goodsSubCategory;

    private String like;  // 좋아요 여부 "Y" "N"

    private int likeCnt;  // 좋아요 개수
    
    private int kcal;  // 칼로리
    
    private int protein;  // 단백질

    private int fat;  // 지방
    
    private int natrium;  // 나트륨

    private int weight;  // 중량
    
    private int sugar; // 당류

    private String regTime;

    private MultipartFile goodsImageFile;  // 파일을 담는 용도

    private String originalGoodsImageName;  // 원본 파일 이름

    private String storedGoodsImageName;  // 서버 저장용 파일 이름

    // 테스트용 이미지X
    public GoodsDto(Long num, String name, int price, int stock, String category, LocalDateTime createTime){
        this.num = num;
        this.goodsName = name;
        this.price = price;
        this.stock = stock;
        this.goodsCategory = category;
        this.regTime = createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    // 관리자페이지용
    public GoodsDto(Long num, String name, int price, int stock, String category, LocalDateTime createTime, String storedFileName){
        this.num = num;
        this.goodsName = name;
        this.price = price;
        this.stock = stock;
        this.goodsCategory = category;
        this.regTime = createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.storedGoodsImageName = storedFileName;
    }
    
    // 유저페이지 메뉴 리스트용
    public GoodsDto(Long num, String name, int price, String storedFileName){
        this.num = num;
        this.goodsName = name;
        this.price = price;
        this.storedGoodsImageName = storedFileName;
    }

    public static GoodsDto entityToGoodsDto(GoodsEntity goodsEntity) {
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setNum(goodsEntity.getNum());
        goodsDto.setGoodsName(goodsEntity.getName());
        goodsDto.setPrice(goodsEntity.getPrice());
        goodsDto.setStock(goodsEntity.getStock());
        goodsDto.setGoodsCategory(goodsEntity.getGoodsSubCategory().getCategoryName());
        goodsDto.setGoodsExplanation(goodsEntity.getExplanation());
        goodsDto.setSellCnt(goodsEntity.getSellCnt());
        goodsDto.setLikeCnt(goodsEntity.getLikeCnt());
        //goodsDto.setStoredGoodsImageName(goodsEntity.getGoodsImageEntity().getStoredFileNameWithExtension());
        goodsDto.setRegTime(goodsEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        goodsDto.setKcal(goodsEntity.getKcal());
        goodsDto.setProtein(goodsEntity.getProtein());
        goodsDto.setFat(goodsEntity.getFat());
        goodsDto.setNatrium(goodsEntity.getNatrium());
        goodsDto.setSugar(goodsEntity.getSugar());
        goodsDto.setWeight(goodsEntity.getWeight());
        return goodsDto;
    }
}
