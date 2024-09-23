package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.enumClass.PaymentMethod;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long submitGoodsNum;
    private String submitPost;
    private String submitAddr;
    private String submitAddrDetail;
    private String submitOrderMessageChoice;
    private int submitUseAccumulatedMoney;
    private PaymentMethod submitPaymentMethod;
    private int submitAmount;
    private int submitGoodsTotalPrice;
    private int submitFinalPrice;
    private int submitAccumulatedMoney;

}