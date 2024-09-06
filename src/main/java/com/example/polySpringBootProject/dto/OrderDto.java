package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.OrderEntity;
import com.example.polySpringBootProject.enumClass.DeliveryType;
import com.example.polySpringBootProject.enumClass.PaymentMethod;
import lombok.*;
import org.aspectj.weaver.ast.Or;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

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

    public OrderDto(long orderUniqueNumber, DeliveryType deliveryType,
                    String goodsName, int submitFinalPrice, int submitAmount){
        this.orderUniqueNumber = orderUniqueNumber;
        this.deliveryType = deliveryType;
        this.goodsName = goodsName;
        this.submitFinalPrice = submitFinalPrice;
        this.submitAmount = submitAmount;
    }


    public static OrderDto entityToDto(OrderEntity orderEntity){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNum(orderEntity.getNum());
        orderDto.setOrderUniqueNumber(orderEntity.getOrderUniqueNumber());
        orderDto.setDeliveryType(orderEntity.getDeliveryType());
        orderDto.setSubmitAddr(orderEntity.getSubmitAddr());
        orderDto.setSubmitAddr(orderEntity.getSubmitAddr());
        orderDto.setSubmitAddrDetail(orderEntity.getSubmitAddrDetail());
        orderDto.setSubmitUseAccumulatedMoney(orderEntity.getSubmitUseAccumulatedMoney());
        orderDto.setSubmitPaymentMethod(orderEntity.getPaymentMethod());
        orderDto.setSubmitAmount(orderEntity.getSubmitAmount());
        orderDto.setSubmitDeliveryPrice(orderEntity.getSubmitDeliveryPrice());
        orderDto.setSubmitFinalPrice(orderEntity.getSubmitFinalPrice());
        orderDto.setSubmitAccumulatedMoney(orderEntity.getSubmitAccumulatedMoney());
        orderDto.setGoodsName(orderEntity.getGoods().getName());
        return orderDto;

    }
}