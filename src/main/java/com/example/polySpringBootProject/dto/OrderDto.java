package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.OrderEntity;
import com.example.polySpringBootProject.enumClass.DeliveryType;
import com.example.polySpringBootProject.enumClass.PaymentMethod;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

}