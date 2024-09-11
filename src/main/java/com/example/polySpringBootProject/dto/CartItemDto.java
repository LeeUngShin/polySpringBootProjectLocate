package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.CartEntity;
import com.example.polySpringBootProject.entity.CartItemEntity;
import lombok.*;

@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private Long cartItemNum;
    private Long goodsNum;
    private String goodsName;
    private int goodsCartAmount;
    private int stock;
    private int goodsPrice;
    private String storedGoodsImageName;

    // 이미지 없는 경우
    public CartItemDto(Long cartItemNum, Long goodsNum, String goodsName, int goodsCartAmount, int price){
        this.cartItemNum = cartItemNum;
        this.goodsNum = goodsNum;
        this.goodsName =  goodsName;
        this.goodsCartAmount = goodsCartAmount;
    }

    // 이미지 있는 경우
    public CartItemDto(Long cartItemNum, Long goodsNum, String goodsName, int goodsCartAmount, int price, String storedGoodsImageName){
        this.cartItemNum = cartItemNum;
        this.goodsNum = goodsNum;
        this.goodsName =  goodsName;
        this.goodsCartAmount = goodsCartAmount;
        this.storedGoodsImageName = storedGoodsImageName;
    }
    
    // 이미지 없는 경우
    public static CartItemDto entityToDtoNotImg(CartItemEntity cartItemEntity){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCartItemNum(cartItemEntity.getNum());
        cartItemDto.setGoodsNum(cartItemEntity.getGoods().getNum());
        cartItemDto.setGoodsName(cartItemEntity.getGoods().getName());
        cartItemDto.setGoodsCartAmount(cartItemEntity.getGoodsAmount());
        cartItemDto.setStock(cartItemEntity.getGoods().getStock());
        cartItemDto.setGoodsPrice(cartItemEntity.getGoods().getPrice());
        return cartItemDto;
    }
    
    // 이미지 있는 경우
    public static CartItemDto entityToDto(CartItemEntity cartItemEntity){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCartItemNum(cartItemEntity.getNum());
        cartItemDto.setGoodsNum(cartItemEntity.getGoods().getNum());
        cartItemDto.setGoodsName(cartItemEntity.getGoods().getName());
        cartItemDto.setGoodsCartAmount(cartItemEntity.getGoodsAmount());
        cartItemDto.setStoredGoodsImageName(cartItemEntity.getGoods().getGoodsImageEntity().get(0).getStoredFileNameWithExtension());
        cartItemDto.setStock(cartItemEntity.getGoods().getStock());
        cartItemDto.setGoodsPrice(cartItemEntity.getGoods().getPrice());
        return cartItemDto;
    }
}
