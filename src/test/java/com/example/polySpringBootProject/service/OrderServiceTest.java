package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.OrderDetailDto;
import com.example.polySpringBootProject.dto.OrderDto;
import com.example.polySpringBootProject.entity.*;
import com.example.polySpringBootProject.enumClass.MemberGrade;
import com.example.polySpringBootProject.enumClass.PaymentMethod;
import com.example.polySpringBootProject.enumClass.RoleType;
import com.example.polySpringBootProject.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    GoodsCategoryRepository goodsCategoryRepository;
    @Autowired
    GoodsSubCategoryRepository goodsSubCategoryRepository;
    @Autowired
    GoodsImageRepository goodsImageRepository;
    @Autowired
    OrderService orderService;

    @Test
    MemberEntity memberInsert(){
        MemberEntity memberEntity1 = MemberEntity.builder()
                .id("testUser2")
                .pw("12345")
                .name("테스트유저")
                .post("00000")
                .addr("주소01")
                .addrDetail("상세주소01")
                .email("testUser@test.org")
                .approval("Y")
                .role(RoleType.ROLE_USER)
                .grade(MemberGrade.BRONZE)
                .build();
        MemberEntity saveMember1 = memberRepository.save(memberEntity1);
        return saveMember1;
    }

    @Test
    GoodsEntity goodsInsert(){
        GoodsCategoryEntity goodsCategoryEntity1 = goodsCategoryRepository.findById(1L).get();
        GoodsSubCategoryEntity subCategoryEntity1 = goodsSubCategoryRepository.findByCategoryName("식빵").get();
        List<String> alleryList = new ArrayList<>();
        alleryList.add("대두");
        alleryList.add("밀");
        GoodsEntity goodsEntity = GoodsEntity.builder()
                .name("테스타상품이름")
                .price(1000)
                .stock(10)
                .explanation("테스트상품설명")
                .del("N")
                .kcal(10)
                .protein(21)
                .fat(15)
                .natrium(12)
                .sugar(5)
                .weight(85)
                .sellCnt(5)
                .likeCnt(3)
                .allergy(alleryList)
                .goodsCategory(goodsCategoryEntity1)
                .goodsSubCategory(subCategoryEntity1)
                .build();
        GoodsEntity savedGoodsEntity = goodsRepository.save(goodsEntity);
        return savedGoodsEntity;
    }

    @Test
    void orderComplete() {

        MemberEntity member = memberInsert();
        System.out.println("주문 회원 아이디 : " + member.getId());
        GoodsEntity goods = goodsInsert();
        System.out.println("주문 상품 이름 : " + goods.getName());

        OrderDto orderDto = OrderDto.builder()
                .paymentMethod(PaymentMethod.BANK_TRANSFER)
                .post("22222")
                .orderAddr("테스트주소")
                .orderAddrDetail("테스트상세주소")
                .orderGoodsAmount(2)
                .orderGoodsTotalPrice(3000)
                .build();
        OrderDetailDto orderDetailDto = orderService.orderComplete(orderDto, member.getId(), goods.getNum());
        assertEquals(member.getId(), orderDetailDto.getOrderMemberId());
        assertEquals(goods.getName(), orderDetailDto.getOrderGoodsName());
        assertEquals(orderDto.getPost(), orderDetailDto.getPost());
    }
}