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

    private String goodsCategory;

    private String regTime;

    private MultipartFile goodsImageFile;  // 파일을 담는 용도

    private String originalGoodsImageName;  // 원본 파일 이름

    private String storedGoodsImageName;  // 서버 저장용 파일 이름


    public GoodsDto(Long num, int price, int stock, String category, LocalDateTime createTime){
        this.num = num;
        this.price = price;
        this.stock = stock;
        this.goodsCategory = category;
        this.regTime = createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static GoodsDto entityToGoodsDto(GoodsEntity goodsEntity) {
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setNum(goodsEntity.getNum());
        goodsDto.setPrice(goodsEntity.getPrice());
        goodsDto.setStock(goodsEntity.getStock());
        goodsDto.setGoodsCategory(goodsEntity.getGoodsCategory().getCategoryName());
        goodsDto.setStoredGoodsImageName(goodsEntity.getGoodsImageEntity().getStoredFileName());
        return goodsDto;
    }
}
