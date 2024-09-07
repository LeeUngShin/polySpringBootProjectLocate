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
import org.aspectj.weaver.Member;
import org.aspectj.weaver.ast.Or;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
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
                .submitDeliveryPrice(orderDto.getSubmitDeliveryPrice())
                .submitAccumulatedMoney(orderDto.getSubmitAccumulatedMoney())
                .paymentMethod(orderDto.getSubmitPaymentMethod())
                .goods(goods)
                .member(member)
                .build();
        try {
            OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

            OrderDetailDto orderDetailDto = OrderDetailDto.EntityToDtoOrderComplete(savedOrderEntity);

            return orderDetailDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("주문처리 중 에러 발생했습니다.");
        }
    }

    public Page<OrderDetailDto> orderList(Pageable pageable, String loginId){
        
        int page = pageable.getPageNumber()-1;  // 0(처음페이지)부터 시작
        int pageLimit = 5; // 한페이지에 보여줄 주문 개수
        
        Page<OrderEntity> orderEntityPage = orderRepository.findByMemberId(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "createdTime")), loginId);

        Page<OrderDetailDto> orderDetailDtoPage = orderEntityPage.map
                (order -> {  // 이미지가 없으면 null예외 발생 -> null 체크 먼저하기!!
                    if(order.getGoods().getGoodsImageEntity() == null || order.getGoods().getGoodsImageEntity().isEmpty()){
                        return new OrderDetailDto(order.getNum(), order.getOrderUniqueNumber(), order.getDeliveryType(), order.getGoods().getName(), order.getSubmitFinalPrice(), order.getSubmitAmount(), order.getCreatedTime());
                    }
                    else {
                        return new OrderDetailDto(order.getNum(), order.getOrderUniqueNumber(), order.getDeliveryType(), order.getGoods().getName(), order.getSubmitFinalPrice(), order.getSubmitAmount(), order.getCreatedTime(), order.getGoods().getGoodsImageEntity().get(0).getStoredFileNameWithExtension());
                    }
                });
        
        return orderDetailDtoPage;

    }

}
