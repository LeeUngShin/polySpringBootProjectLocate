package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.entity.GoodsCategoryEntity;
import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.repository.GoodsCategoryRepository;
import com.example.polySpringBootProject.repository.GoodsImageRepository;
import com.example.polySpringBootProject.repository.GoodsRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsCategoryRepository goodsCategoryRepository;

    @Autowired
    GoodsImageRepository goodsImageRepository;


    @Test
    @Transactional
    void goodsRegister() {
        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();

            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("등록상품명")
                    .price(1000)
                    .stock(100)
                    .explanation("상품설명")
                    .del("N")
                    .goodsCategory(goodsCategoryEntity)
                    .build();
            GoodsEntity goodsEntity1 =  goodsRepository.save(goodsEntity);

            assertEquals("등록상품명", goodsEntity1.getName());
    }

    @Test
    void delete(){
        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();

        GoodsEntity goodsEntity = GoodsEntity.builder()
                .name("등록상품명")
                .price(1000)
                .stock(100)
                .explanation("상품설명")
                .del("N")
                .goodsCategory(goodsCategoryEntity)
                .build();
        GoodsEntity goodsEntity1 =  goodsRepository.save(goodsEntity);
        goodsRepository.delete(goodsEntity1);

    }

}