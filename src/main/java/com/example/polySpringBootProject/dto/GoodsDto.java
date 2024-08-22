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

    private int sellCnt;

    private String goodsCategory;

    private String goodsSubCategory;

    private String like;  // 좋아요 여부 "Y" "N"

    private int likeCnt;  // 좋아요 개수

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

    public GoodsDto(Long num, String name, int price, int stock, String category, LocalDateTime createTime, String storedFileName){
        this.num = num;
        this.goodsName = name;
        this.price = price;
        this.stock = stock;
        this.goodsCategory = category;
        this.regTime = createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.storedGoodsImageName = storedFileName;
    }

    public static GoodsDto entityToGoodsDto(GoodsEntity goodsEntity) {
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setNum(goodsEntity.getNum());
        goodsDto.setGoodsName(goodsEntity.getName());
        goodsDto.setPrice(goodsEntity.getPrice());
        goodsDto.setStock(goodsEntity.getStock());
        goodsDto.setGoodsCategory(goodsEntity.getGoodsCategory().getCategoryName());
        goodsDto.setGoodsExplanation(goodsEntity.getExplanation());
        goodsDto.setSellCnt(goodsEntity.getSellCnt());
        goodsDto.setLikeCnt(goodsEntity.getLikeCnt());
        goodsDto.setStoredGoodsImageName(goodsEntity.getGoodsImageEntity().getStoredFileNameWithExtension());
        goodsDto.setRegTime(goodsEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return goodsDto;
    }
}
