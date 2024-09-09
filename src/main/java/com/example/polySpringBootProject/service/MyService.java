package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.OrderDetailDto;
import com.example.polySpringBootProject.entity.OrderEntity;
import com.example.polySpringBootProject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    OrderRepository orderRepository;

    public Page<OrderDetailDto> orderList(Pageable pageable, String loginId){

        int page = pageable.getPageNumber()-1;  // 0(처음페이지)부터 시작
        int pageLimit = 5; // 한페이지에 보여줄 주문 개수

        Page<OrderEntity> orderEntityPage = orderRepository.findByMemberId(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "createdTime")), loginId);

        Page<OrderDetailDto> orderDetailDtoPage = orderEntityPage.map
                (order -> {  // 이미지가 없으면 null예외 발생 -> null 체크 먼저하기!!
                    if(order.getGoods().getGoodsImageEntity() == null || order.getGoods().getGoodsImageEntity().isEmpty()){
                        return new OrderDetailDto(order.getNum(), order.getOrderUniqueNumber(), order.getDeliveryType(), order.getGoods().getName(), order.getSubmitFinalPrice(), order.getSubmitAmount(), order.getCreatedTime(), order.getGoods().getNum());
                    }
                    else {
                        return new OrderDetailDto(order.getNum(), order.getOrderUniqueNumber(), order.getDeliveryType(), order.getGoods().getName(), order.getSubmitFinalPrice(), order.getSubmitAmount(), order.getCreatedTime(), order.getGoods().getGoodsImageEntity().get(0).getStoredFileNameWithExtension(), order.getGoods().getNum());
                    }
                });

        return orderDetailDtoPage;

    }
}
