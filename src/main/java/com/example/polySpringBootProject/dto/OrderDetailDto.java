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
    private Long num;

    private long orderUniqueNumber;

    private String orderDateTime;

    private PaymentMethod paymentMethod;

    private String post;

    private String orderAddr;

    private String orderAddrDetail;

    private String orderGoodsName;

    private int orderGoodsAmount;

    private String orderMemberId;

    private DeliveryType deliveryType;

    private int orderGoodsTotalPrice;

    private String goodsStoredImgName;
    
    // 이미지 존재
    public OrderDetailDto(Long num, int orderUniqueNumber, LocalDateTime orderDateTime,
                        PaymentMethod paymentMethod, String post, String orderAddr,
                          String orderAddrDetail, String orderGoodsName,
                          int orderGoodsAmount, DeliveryType deliveryType,
                          int orderGoodsTotalPrice, String goodsStoredImgName ){

        this.num = num;
        this.orderDateTime = orderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.paymentMethod = paymentMethod;
        this.post = post;
        this.orderAddr = orderAddr;
        this.orderAddrDetail = orderAddrDetail;
        this.orderGoodsName = orderGoodsName;
        this.orderGoodsAmount = orderGoodsAmount;
        this.deliveryType = deliveryType;
        this.orderGoodsTotalPrice = orderGoodsTotalPrice;
        this.goodsStoredImgName = goodsStoredImgName;
    }

    // 이미지 존재X
    public OrderDetailDto(Long num, int orderUniqueNumber, LocalDateTime orderDateTime,
                          PaymentMethod paymentMethod, String post, String orderAddr,
                          String orderAddrDetail, String orderGoodsName,
                          int orderGoodsAmount, DeliveryType deliveryType,
                          int orderGoodsTotalPrice){

        this.num = num;
        this.orderDateTime = orderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.paymentMethod = paymentMethod;
        this.post = post;
        this.orderAddr = orderAddr;
        this.orderAddrDetail = orderAddrDetail;
        this.orderGoodsName = orderGoodsName;
        this.orderGoodsAmount = orderGoodsAmount;
        this.deliveryType = deliveryType;
        this.orderGoodsTotalPrice = orderGoodsTotalPrice;
    }

    public static OrderDetailDto EntityToDtoOrderComplete(OrderEntity orderEntity){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setOrderUniqueNumber(orderEntity.getOrderUniqueNumber());
        orderDetailDto.setOrderDateTime(orderEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        orderDetailDto.setPaymentMethod(orderEntity.getPaymentMethod());
        orderDetailDto.setPost(orderEntity.getOrderPost());
        orderDetailDto.setOrderAddr(orderEntity.getOrderAddr());
        orderDetailDto.setOrderAddrDetail(orderEntity.getOrderAddrDetail());
        orderDetailDto.setOrderMemberId(orderEntity.getMember().getId());
        orderDetailDto.setOrderGoodsName(orderEntity.getGoods().getName());
        orderDetailDto.setOrderGoodsAmount(orderEntity.getOrderGoodsAmount());
        orderDetailDto.setOrderGoodsTotalPrice(orderEntity.getTotalPrice());
        return orderDetailDto;
    }

    public static OrderDetailDto EntityToDto(OrderEntity orderEntity){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setNum(orderEntity.getNum());
        orderDetailDto.setOrderUniqueNumber(orderEntity.getOrderUniqueNumber());
        orderDetailDto.setOrderDateTime(orderEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        orderDetailDto.setPaymentMethod(orderEntity.getPaymentMethod());
        orderDetailDto.setPost(orderEntity.getOrderPost());
        orderDetailDto.setOrderAddr(orderEntity.getOrderAddr());
        orderDetailDto.setOrderAddrDetail(orderEntity.getOrderAddrDetail());
        orderDetailDto.setOrderGoodsName(orderEntity.getGoods().getName());
        orderDetailDto.setOrderGoodsAmount(orderEntity.getOrderGoodsAmount());
        orderDetailDto.setOrderMemberId(orderEntity.getMember().getId());
        orderDetailDto.setDeliveryType(orderEntity.getDeliveryType());
        orderDetailDto.setOrderGoodsTotalPrice(orderEntity.getTotalPrice());
        // 이미지 존재하면
        if(orderEntity.getGoods().getGoodsImageEntity()!=null && orderEntity.getGoods().getGoodsImageEntity().isEmpty()){
            orderDetailDto.setGoodsStoredImgName(orderEntity.getGoods().getGoodsImageEntity().get(0).getStoredFileNameWithExtension());
        }
        return orderDetailDto;
    }
}
