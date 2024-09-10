package com.example.polySpringBootProject.enumClass;

public enum DeliveryType {

    READY("배송준비중"),
    START("배송출발"),
    DELIVERY_ING("배송중"),
    COMPLETE("배송완료"),
    CANCEL("주문취소");

    private final String delivery;

    DeliveryType(String delivery) {
        this.delivery = delivery;
    }

    public String getDelivery(){
        return delivery;
    }
}
