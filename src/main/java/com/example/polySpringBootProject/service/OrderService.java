package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.dto.MemberDto;
import com.example.polySpringBootProject.dto.OrderDetailDto;
import com.example.polySpringBootProject.dto.OrderDto;
import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.entity.OrderEntity;
import com.example.polySpringBootProject.enumClass.DeliveryType;
import com.example.polySpringBootProject.repository.GoodsRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import com.example.polySpringBootProject.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    GoodsRepository goodsRepository;

    public MemberDto loginMemberInfo(String id){
        System.out.println("회원 아이디 : " +id);
        MemberEntity member = memberRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("해당 회원의 정보를 찾을 수 없습니다."));
        MemberDto memberDto = MemberDto.entityToDto(member);
        return memberDto;
    }

    public GoodsDto orderGoodsInfo(Long goodsNum) {
        GoodsEntity goods = goodsRepository.findById(goodsNum).orElseThrow(() -> new EntityNotFoundException("해당 상품을 찾을 수 없습니다."));
        if (goods.getGoodsImageEntity() == null || goods.getGoodsImageEntity().isEmpty()) {
            GoodsDto goodsDto = GoodsDto.entityToGoodsDtoNotImg(goods);
            return goodsDto;
        } else {
            GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goods);
            return goodsDto;
        }
    }


    public OrderDetailDto orderComplete(OrderDto orderDto, String loginId, Long goodsNum) {

        MemberEntity member = memberRepository.findById(loginId).orElseThrow(() -> new EntityNotFoundException("해당 아이디를 찾을 수 없습니다."));
        GoodsEntity goods = goodsRepository.findById(goodsNum).orElseThrow(() -> new EntityNotFoundException("해당 상품을 찾을 수 없습니다."));

        long orderUniqueNumber = System.currentTimeMillis() + member.getNum();

        OrderEntity orderEntity = OrderEntity.builder()
                .orderUniqueNumber(orderUniqueNumber)
                .submitPost(orderDto.getSubmitPost())
                .submitAddr(orderDto.getSubmitAddr())
                .submitAddrDetail(orderDto.getSubmitAddrDetail())
                .deliveryType(DeliveryType.READY)
                .submitOrderMessageChoice(orderDto.getSubmitOrderMessageChoice())
                .submitUseAccumulatedMoney(orderDto.getSubmitUseAccumulatedMoney())
                .submitAmount(orderDto.getSubmitAmount())
                .submitGoodsTotalPrice(orderDto.getSubmitGoodsTotalPrice())
                .submitFinalPrice(orderDto.getSubmitFinalPrice())
                .submitAccumulatedMoney(orderDto.getSubmitAccumulatedMoney())
                .paymentMethod(orderDto.getSubmitPaymentMethod())
                .goods(goods)
                .member(member)
                .build();
        try {
            OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
            goods.setStock(goods.getStock() - orderDto.getSubmitAmount());
            goods.setSellCnt(goods.getSellCnt()+1);
            goodsRepository.save(goods);
            member.setAccumulatedMoney(orderDto.getSubmitAccumulatedMoney());
            memberRepository.save(member);
            OrderDetailDto orderDetailDto = OrderDetailDto.EntityToDtoOrderComplete(savedOrderEntity);

            return orderDetailDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("주문처리 중 에러 발생했습니다.");
        }
    }


}
