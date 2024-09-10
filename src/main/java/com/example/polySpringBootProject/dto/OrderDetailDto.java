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
    private String submitPaymentMethodStr;
    private int submitAmount;
    private int submitGoodsTotalPrice;
    private int submitDeliveryPrice;
    private int submitFinalPrice;
    private int submitAccumulatedMoney;
    private String goodsName;

    private Long goodsNum;
    private String memberName;
    private String phone;
    private String phone1;
    private String phone2;
    private String phone3;
    private Long orderNum;
    private long orderUniqueNumber;
    private DeliveryType deliveryType;
    private String deliveryTypeStr;
    private String orderDate;

    private String goodsStoredImgName;
    
    // 이미지 존재
    public OrderDetailDto(Long orderNum, long orderUniqueNumber, DeliveryType deliveryType,
                          String orderGoodsName, int orderGoodsFinalPrice,
                          int orderGoodsAmount, LocalDateTime orderDate, String goodsStoredImgName,
                          Long goodsNum){
        this.orderNum = orderNum;
        this.goodsNum = goodsNum;
        this.orderUniqueNumber = orderUniqueNumber;
        this.deliveryType = deliveryType;
        this.deliveryTypeStr = deliveryType.getDelivery();
        this.goodsName = orderGoodsName;
        this.submitFinalPrice = orderGoodsFinalPrice;
        this.submitAmount = orderGoodsAmount;
        this.orderDate = orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.goodsStoredImgName = goodsStoredImgName;
    }

    // 이미지 존재X
    public OrderDetailDto(Long orderNum, long orderUniqueNumber, DeliveryType deliveryType,
                          String orderGoodsName, int orderGoodsFinalPrice,
                          int orderGoodsAmount, LocalDateTime orderDate,
                          Long goodsNum){
        this.orderNum = orderNum;
        this.goodsNum = goodsNum;
        this.orderUniqueNumber = orderUniqueNumber;
        this.deliveryType = deliveryType;
        this.deliveryTypeStr = deliveryType.getDelivery();
        this.goodsName = orderGoodsName;
        this.submitFinalPrice = orderGoodsFinalPrice;
        this.submitAmount = orderGoodsAmount;
        this.orderDate = orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static OrderDetailDto EntityToDtoOrderComplete(OrderEntity orderEntity){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setMemberName(orderEntity.getMember().getName());
        orderDetailDto.setOrderUniqueNumber(orderEntity.getOrderUniqueNumber());
        orderDetailDto.setOrderDate(orderEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        orderDetailDto.setSubmitPaymentMethod(orderEntity.getPaymentMethod());
        orderDetailDto.setSubmitPost(orderEntity.getSubmitPost());
        orderDetailDto.setSubmitAddr(orderEntity.getSubmitAddr());
        orderDetailDto.setSubmitAddrDetail(orderEntity.getSubmitAddrDetail());
        orderDetailDto.setGoodsName(orderEntity.getGoods().getName());
        orderDetailDto.setSubmitAmount(orderEntity.getSubmitAmount());
        orderDetailDto.setSubmitGoodsTotalPrice(orderEntity.getSubmitGoodsTotalPrice());
        orderDetailDto.setSubmitFinalPrice(orderEntity.getSubmitFinalPrice());
        orderDetailDto.setSubmitUseAccumulatedMoney(orderEntity.getSubmitUseAccumulatedMoney());
        orderDetailDto.setSubmitDeliveryPrice(orderEntity.getSubmitDeliveryPrice());
        orderDetailDto.setSubmitOrderMessageChoice(orderEntity.getSubmitOrderMessageChoice());
        orderDetailDto.setPhone1(orderEntity.getMember().getPhone().substring(0,3));
        orderDetailDto.setPhone2(orderEntity.getMember().getPhone().substring(3,7));
        orderDetailDto.setPhone3(orderEntity.getMember().getPhone().substring(7,11));
        // 이미지 존재하면
        if(orderEntity.getGoods().getGoodsImageEntity()!=null && !orderEntity.getGoods().getGoodsImageEntity().isEmpty()){
            orderDetailDto.setGoodsStoredImgName(orderEntity.getGoods().getGoodsImageEntity().get(0).getStoredFileNameWithExtension());
        }
        return orderDetailDto;
    }


    public static OrderDetailDto EntityToDtoDetailOrder(OrderEntity orderEntity){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setGoodsNum(orderEntity.getGoods().getNum());
        orderDetailDto.setOrderNum(orderEntity.getNum());
        orderDetailDto.setMemberName(orderEntity.getMember().getName());
        orderDetailDto.setOrderUniqueNumber(orderEntity.getOrderUniqueNumber());
        orderDetailDto.setDeliveryType(orderEntity.getDeliveryType());
        orderDetailDto.setDeliveryTypeStr(orderEntity.getDeliveryType().getDelivery());
        orderDetailDto.setSubmitAddr(orderEntity.getSubmitAddr());
        orderDetailDto.setSubmitAddr(orderEntity.getSubmitAddr());
        orderDetailDto.setSubmitAddrDetail(orderEntity.getSubmitAddrDetail());
        orderDetailDto.setSubmitUseAccumulatedMoney(orderEntity.getSubmitUseAccumulatedMoney());
        orderDetailDto.setSubmitPaymentMethod(orderEntity.getPaymentMethod());
        orderDetailDto.setSubmitPaymentMethodStr(orderEntity.getPaymentMethod().getPaymentMethodStr());
        orderDetailDto.setSubmitAmount(orderEntity.getSubmitAmount());
        orderDetailDto.setSubmitDeliveryPrice(orderEntity.getSubmitDeliveryPrice());
        orderDetailDto.setSubmitGoodsTotalPrice(orderEntity.getSubmitGoodsTotalPrice());
        orderDetailDto.setSubmitFinalPrice(orderEntity.getSubmitFinalPrice());
        orderDetailDto.setSubmitAccumulatedMoney(orderEntity.getSubmitAccumulatedMoney());
        orderDetailDto.setSubmitOrderMessageChoice(orderEntity.getSubmitOrderMessageChoice());
        orderDetailDto.setGoodsName(orderEntity.getGoods().getName());
        orderDetailDto.setOrderDate(orderEntity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))    );
        orderDetailDto.setPhone1(orderEntity.getMember().getPhone().substring(0,3));
        orderDetailDto.setPhone2(orderEntity.getMember().getPhone().substring(3,7));
        orderDetailDto.setPhone3(orderEntity.getMember().getPhone().substring(7,11));
        // 이미지 존재하면
        if(orderEntity.getGoods().getGoodsImageEntity()!=null && !orderEntity.getGoods().getGoodsImageEntity().isEmpty()){
            orderDetailDto.setGoodsStoredImgName(orderEntity.getGoods().getGoodsImageEntity().get(0).getStoredFileNameWithExtension());
        }
        return orderDetailDto;
    }
}
