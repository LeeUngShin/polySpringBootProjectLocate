package com.example.polySpringBootProject.enumClass;

public enum PaymentMethod {

    CREDIT_CARD("신용카드"),
    BANK_TRANSFER("계좌이체"),
    PAPAL("페이팔"),
    NAVER_PAY("네이버페이");
    
    private final String paymentMethodStr;
    
    PaymentMethod(String paymentMethodStr){
        this.paymentMethodStr = paymentMethodStr;
    }

    public String getPaymentMethodStr(){
        return paymentMethodStr;
    }
}