package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.enumClass.DeliveryType;
import com.example.polySpringBootProject.enumClass.PaymentMethod;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private PaymentMethod paymentMethod;

    private String post;

    private String orderAddr;

    private String orderAddrDetail;

    private int orderGoodsAmount;

    private int orderGoodsTotalPrice;
}