package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.CartItemDto;
import com.example.polySpringBootProject.dto.OrderDetailDto;
import com.example.polySpringBootProject.entity.*;
import com.example.polySpringBootProject.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;

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

    public OrderDetailDto orderDetail(Long orderNum){
        OrderEntity order = orderRepository.findById(orderNum).orElseThrow(()-> new EntityNotFoundException("해당 주문을 찾을 수 없습니다."));

        OrderDetailDto orderDetailDto = OrderDetailDto.EntityToDtoDetailOrder(order);

        return orderDetailDto;
    }

    public boolean cartInput(CartItemDto cartItemDto, String loginId, Long goodsNum){

        CartEntity myCartEntity = null;
        if(!cartRepository.existsByMemberId(loginId)){  // 로그인한 회원의 장바구니 존재하지 않으면
            MemberEntity member = memberRepository.findById(loginId).orElseThrow(()-> new EntityNotFoundException("해당 회원을 찾을 수 없습니다."));
            CartEntity cartEntity = CartEntity.builder()
                    .member(member)
                    .build();
            myCartEntity = cartRepository.save(cartEntity);  // 장바구니 생성
        }
        else{
            myCartEntity = cartRepository.findByMemberId(loginId);
        }

        GoodsEntity goods = goodsRepository.findById(goodsNum).orElseThrow(()-> new EntityNotFoundException("해당 상품을 찾을 수 없습니다."));

        Optional<CartItemEntity> cartItemEntity = cartItemRepository.findByCartEntityNumAndGoodsNum(myCartEntity.getNum(), goodsNum);
        if(cartItemEntity.isEmpty()){
            CartItemEntity cartItem = CartItemEntity.builder()
                    .cartEntity(myCartEntity)
                    .goods(goods)
                    .goodsAmount(cartItemDto.getGoodsCartAmount())
                    .build();

            CartItemEntity savedCartItemEntity = cartItemRepository.save(cartItem);
            if(savedCartItemEntity == null) return false;
            else return true;
        }
        else{
            CartItemEntity cartItem = cartItemEntity.get();
            cartItem.addGoodsAmount(cartItemDto.getGoodsCartAmount());
            CartItemEntity savedCartItemEntity = cartItemRepository.save(cartItem);
            if(savedCartItemEntity == null) return false;
            else return true;
        }
    }

    public List<CartItemDto> getCartItemList(String loginId){
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findByCartEntityMemberId(loginId);

        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for(CartItemEntity cartItemEntity : cartItemEntityList){
            if(cartItemEntity.getGoods().getGoodsImageEntity()==null || cartItemEntity.getGoods().getGoodsImageEntity().isEmpty()) {
                CartItemDto cartItemDto = CartItemDto.entityToDtoNotImg(cartItemEntity);
                cartItemDtoList.add(cartItemDto);
            }
            else{
                CartItemDto cartItemDto = CartItemDto.entityToDto(cartItemEntity);
                cartItemDtoList.add(cartItemDto);
            }
        }
        return cartItemDtoList;
    }
}
