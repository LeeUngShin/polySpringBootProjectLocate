package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.OrderEntity;
import com.example.polySpringBootProject.enumClass.DeliveryType;
import com.example.polySpringBootProject.enumClass.PaymentMethod;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDto {
    private String submitPost;
    private String submitAddr;
    private String submitAddrDetail;
    private String submitOrderMessageChoice;
    private int submitUseAccumulatedMoney;
    private PaymentMethod submitPaymentMethod;
    private int submitAmount;
    private int submitDeliveryPrice;
    private int submitFinalPrice;
    private int submitAccumulatedMoney;
    private String goodsName;

    private Long orderNum;
    private long orderUniqueNumber;
    private DeliveryType deliveryType;
    private String orderDate;

    private String goodsStoredImgName;
    
    // 이미지 존재
    public OrderDetailDto(Long orderNum, long orderUniqueNumber, DeliveryType deliveryType,
                          String orderGoodsName, int orderGoodsFinalPrice,
                          int orderGoodsAmount, LocalDateTime orderDate, String goodsStoredImgName){
        this.orderNum = orderNum;
        this.orderUniqueNumber = orderUniqueNumber;
        this.deliveryType = deliveryType;
        this.goodsName = orderGoodsName;
        this.submitFinalPrice = orderGoodsFinalPrice;
        this.submitAmount = orderGoodsAmount;
        this.orderDate = orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.goodsStoredImgName = goodsStoredImgName;
    }

    // 이미지 존재X
    public OrderDetailDto(Long orderNum, long orderUniqueNumber, DeliveryType deliveryType,
                          String orderGoodsName, int orderGoodsFinalPrice,
                          int orderGoodsAmount, LocalDateTime orderDate){
        this.orderNum = orderNum;
        this.orderUniqueNumber = orderUniqueNumber;
        this.deliveryType = deliveryType;
        this.goodsName = orderGoodsName;
        this.submitFinalPrice = orderGoodsFinalPrice;
        this.submitAmount = orderGoodsAmount;
        this.orderDate = orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static OrderDetailDto EntityToDtoOrderComplete(OrderEntity orderEntity){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setOrderUniqueNumber(orderEntity.getOrderUniqueNumber());
        orderDetailDto.setOrderDate(orderEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        orderDetailDto.setSubmitPaymentMethod(orderEntity.getPaymentMethod());
        orderDetailDto.setSubmitPost(orderEntity.getSubmitPost());
        orderDetailDto.setSubmitAddr(orderEntity.getSubmitAddr());
        orderDetailDto.setSubmitAddrDetail(orderEntity.getSubmitAddrDetail());
        orderDetailDto.setGoodsName(orderEntity.getGoods().getName());
        orderDetailDto.setSubmitAmount(orderEntity.getSubmitAmount());
        orderDetailDto.setSubmitFinalPrice(orderEntity.getSubmitFinalPrice());
        // 이미지 존재하면
        if(orderEntity.getGoods().getGoodsImageEntity()!=null && orderEntity.getGoods().getGoodsImageEntity().isEmpty()){
            orderDetailDto.setGoodsStoredImgName(orderEntity.getGoods().getGoodsImageEntity().get(0).getStoredFileNameWithExtension());
        }
        return orderDetailDto;
    }

    public static OrderDetailDto EntityToDtoDetailOrder(OrderEntity orderEntity){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        //orderDetailDto.setOrderNum(orderEntity.getNum());
        orderDetailDto.setOrderUniqueNumber(orderEntity.getOrderUniqueNumber());
        orderDetailDto.setDeliveryType(orderEntity.getDeliveryType());
        orderDetailDto.setSubmitAddr(orderEntity.getSubmitAddr());
        orderDetailDto.setSubmitAddr(orderEntity.getSubmitAddr());
        orderDetailDto.setSubmitAddrDetail(orderEntity.getSubmitAddrDetail());
        orderDetailDto.setSubmitUseAccumulatedMoney(orderEntity.getSubmitUseAccumulatedMoney());
        orderDetailDto.setSubmitPaymentMethod(orderEntity.getPaymentMethod());
        orderDetailDto.setSubmitAmount(orderEntity.getSubmitAmount());
        orderDetailDto.setSubmitDeliveryPrice(orderEntity.getSubmitDeliveryPrice());
        orderDetailDto.setSubmitFinalPrice(orderEntity.getSubmitFinalPrice());
        orderDetailDto.setSubmitAccumulatedMoney(orderEntity.getSubmitAccumulatedMoney());
        orderDetailDto.setGoodsName(orderEntity.getGoods().getName());
        orderDetailDto.setOrderDate(orderEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))    );

        // 이미지 존재하면
        if(orderEntity.getGoods().getGoodsImageEntity()!=null && orderEntity.getGoods().getGoodsImageEntity().isEmpty()){
            orderDetailDto.setGoodsStoredImgName(orderEntity.getGoods().getGoodsImageEntity().get(0).getStoredFileNameWithExtension());
        }
        return orderDetailDto;
    }
}
