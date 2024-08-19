package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.RoleType;
import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.entity.*;
import com.example.polySpringBootProject.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class AdminServiceTest {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsCategoryRepository goodsCategoryRepository;

    @Autowired
    GoodsImageRepository goodsImageRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    AdminService adminService;

    @Test
    //@Transactional
    void goodsRegister() {
        // Given
        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();
        GoodsEntity goodsEntity = null;
        goodsEntity = GoodsEntity.builder()
                .name("카테고리1상품")
                .price(1000)
                .stock(100)
                .explanation("카테고리1상품설명")
                .del("N")
                .sellCnt(0)
                .likeCnt(0)
                .goodsCategory(goodsCategoryEntity)
                .build();
        // when
        GoodsEntity saveGoodsEntity =  goodsRepository.save(goodsEntity);
        // then

        Assertions.assertNotNull(saveGoodsEntity.getName());
        Assertions.assertEquals("카테고리1상품", saveGoodsEntity.getName());
    }

    @Test
        //@Transactional
    void goodsRegisters() {
        // Given
        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();
        for(int i=0;i<32;i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("카테고리1상품"+i)
                    .price(1000)
                    .stock(100)
                    .explanation("카테고리1상품설명"+i)
                    .del("N")
                    .sellCnt(0)
                    .likeCnt(0)
                    .goodsCategory(goodsCategoryEntity)
                    .build();

            GoodsImageEntity goodsImageEntity = GoodsImageEntity.builder()
                    .uploadPath("C:/imgUploadF/goods/e9b0c82b72334ad5abf8435e69a7e203.JPG")
                            .goodsEntity(goodsEntity)
                                    .build();
            // when
            goodsRepository.save(goodsEntity);
            goodsImageRepository.save(goodsImageEntity);
        }
        // then

       // Assertions.assertNotNull(saveGoodsEntity.getName());
        //Assertions.assertEquals("카테고리1상품", saveGoodsEntity.getName());
    }
    @Test
    void goodsDetail() {
        // Given
        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();
        GoodsEntity goodsEntity = null;
        goodsEntity = GoodsEntity.builder()
                .name("카테고리1상품")
                .price(1000)
                .stock(100)
                .explanation("카테고리1상품설명")
                .del("N")
                .sellCnt(0)
                .likeCnt(0)
                .goodsCategory(goodsCategoryEntity)
                .build();
        GoodsEntity saveGoodsEntity =  goodsRepository.save(goodsEntity);

        // When
        GoodsEntity goodsEntity1 = goodsRepository.findById(saveGoodsEntity.getNum()).orElse(null);

        Assertions.assertEquals("카테고리1상품", goodsEntity1.getName());


    }

    @Test
    void delete(){
        // Given
        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();

        GoodsEntity goodsEntity = GoodsEntity.builder()
                .name("카테고리1상품")
                .price(1000)
                .stock(100)
                .explanation("카테고리1상품설명")
                .del("N")
                .sellCnt(0)
                .likeCnt(0)
                .goodsCategory(goodsCategoryEntity)
                .build();
        GoodsEntity savedGoodsEntity =  goodsRepository.save(goodsEntity);

        goodsEntity.setDel("Y");
        // When
        GoodsEntity deleteGoodsEntity =  goodsRepository.save(goodsEntity);

        // Then
        Assertions.assertEquals("카테고리1상품", deleteGoodsEntity.getName());
        Assertions.assertEquals("Y", deleteGoodsEntity.getDel());
    }

    @Test
    void goodsModify() {
        // Given
        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();

        GoodsEntity goodsEntity = GoodsEntity.builder()
                .name("카테고리1상품")
                .price(1000)
                .stock(100)
                .explanation("카테고리1상품설명")
                .del("N")
                .sellCnt(0)
                .likeCnt(0)
                .goodsCategory(goodsCategoryEntity)
                .build();
        GoodsEntity savedGoodsEntity =  goodsRepository.save(goodsEntity);
        savedGoodsEntity.setName("수정카테고리1상품");
        
        // When
        GoodsEntity modifyGoodsEntity = goodsRepository.save(savedGoodsEntity);

        // Then
        Assertions.assertEquals("수정카테고리1상품", modifyGoodsEntity.getName());

    }

    @Test
    void goodsList() {
        // Given
        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();
        for(int i=1;i<=34;i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("카테고리1상품"+i)
                    .price(1000)
                    .stock(100)
                    .explanation("카테고리1상품설명"+i)
                    .del("N")
                    .sellCnt(0)
                    .likeCnt(0)
                    .goodsCategory(goodsCategoryEntity)
                    .build();
            goodsRepository.save(goodsEntity);
        }
        int page = 0;
        int pageLimit = 7;
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "num"));


        // When
        Page<GoodsEntity> goodsEntityPage = goodsRepository.findAll(pageable);

        // Then
        List<GoodsEntity> goodsEntityList = goodsEntityPage.getContent();
        Assertions.assertEquals(5, goodsEntityList.size());
        Assertions.assertEquals("카테고리1상품34", goodsEntityList.get(0).getName());
        Assertions.assertEquals(34, goodsEntityPage.getTotalElements()); // 전체 요소 수
        Assertions.assertEquals(7, goodsEntityPage.getTotalPages()); // 총 페이지 수
    }

    @Test
    @Transactional
    void likeAdd() {
        // Given
        MemberEntity memberEntity = MemberEntity.builder()
                .id("user01")
                .pw("12345")
                .name("사용자01")
                .post("00000")
                .addr("주소01")
                .addrDetail("상세주소01")
                .email("user01@test.org")
                .approval("Y")
                .role(RoleType.ROLE_USER)
                .build();
        MemberEntity saveMember = memberRepository.save(memberEntity);

        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();
        GoodsEntity goodsEntity = GoodsEntity.builder()
                .name("카테고리1상품")
                .price(1000)
                .stock(100)
                .explanation("카테고리1상품설명")
                .del("N")
                .sellCnt(0)
                .likeCnt(0)
                .goodsCategory(goodsCategoryEntity)
                .build();
        GoodsEntity saveGoods = goodsRepository.save(goodsEntity);

        // when
        LikeEntity likeEntity = LikeEntity.builder()
                .goods(saveGoods)
                .member(saveMember)
                .build();
        saveGoods.addLike(likeEntity);
        saveMember.addLike(likeEntity);
        LikeEntity savedLike = likeRepository.save(likeEntity);


        // Then
        assertNotNull(savedLike);
        assertEquals(1, saveGoods.getLikeEntitySet().size());
        assertEquals(1, saveMember.getLikeEntitySet().size());
        assertEquals("사용자01", likeEntity.getMember().getName());
        assertTrue(adminService.likePresent(saveMember.getNum(), saveGoods.getNum()));
    }

    @Test
    void likeDelete() {
        MemberEntity memberEntity = MemberEntity.builder()
                .id("user02")
                .pw("12345")
                .name("사용자02")
                .post("00000")
                .addr("주소01")
                .addrDetail("상세주소02")
                .email("user01@test.org")
                .approval("Y")
                .role(RoleType.ROLE_USER)
                .build();
        MemberEntity saveMember = memberRepository.save(memberEntity);

        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();
        GoodsEntity goodsEntity = GoodsEntity.builder()
                .name("카테고리2상품")
                .price(1000)
                .stock(100)
                .explanation("카테고리2상품설명")
                .del("N")
                .sellCnt(0)
                .likeCnt(0)
                .goodsCategory(goodsCategoryEntity)
                .build();
        GoodsEntity saveGoods = goodsRepository.save(goodsEntity);

        // when
        LikeEntity likeEntity = LikeEntity.builder()
                .goods(saveGoods)
                .member(saveMember)
                .build();
        saveGoods.addLike(likeEntity);
        saveMember.addLike(likeEntity);
        LikeEntity savedLike = likeRepository.save(likeEntity);
        likeRepository.deleteById(savedLike.getNum());

        // Then
        LikeEntity like = likeRepository.findById(savedLike.getNum()).orElse(null);
        assertNull(like);

    }

    @Test
    void myLikeGoods() {
        MemberEntity memberEntity1 = MemberEntity.builder()
                .id("user01")
                .pw("12345")
                .name("사용자01")
                .post("00000")
                .addr("주소01")
                .addrDetail("상세주소01")
                .email("user01@test.org")
                .approval("Y")
                .role(RoleType.ROLE_USER)
                .build();
        MemberEntity saveMember1 = memberRepository.save(memberEntity1);

        MemberEntity memberEntity2 = MemberEntity.builder()
                .id("user02")
                .pw("12345")
                .name("사용자02")
                .post("00000")
                .addr("주소01")
                .addrDetail("상세주소02")
                .email("user01@test.org")
                .approval("Y")
                .role(RoleType.ROLE_USER)
                .build();
        MemberEntity saveMember2 = memberRepository.save(memberEntity2);


        GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();
        for(int i=0;i<5;i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("카테고리1상품"+i)
                    .price(1000)
                    .stock(100)
                    .explanation("카테고리1상품설명"+i)
                    .del("N")
                    .sellCnt(0)
                    .likeCnt(0)
                    .goodsCategory(goodsCategoryEntity)
                    .build();
            goodsRepository.save(goodsEntity);

            LikeEntity likeEntity = LikeEntity.builder()
                    .member(memberEntity1)
                    .goods(goodsEntity)
                    .build();
            memberEntity1.addLike(likeEntity);
            goodsEntity.addLike(likeEntity);
            likeRepository.save(likeEntity);
        }

        for(int i=5;i<13;i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("카테고리1상품"+i)
                    .price(1000)
                    .stock(100)
                    .explanation("카테고리1상품설명"+i)
                    .del("N")
                    .sellCnt(0)
                    .likeCnt(0)
                    .goodsCategory(goodsCategoryEntity)
                    .build();
            goodsRepository.save(goodsEntity);

            LikeEntity likeEntity = LikeEntity.builder()
                    .member(memberEntity2)
                    .goods(goodsEntity)
                    .build();
            memberEntity2.addLike(likeEntity);
            goodsEntity.addLike(likeEntity);
            likeRepository.save(likeEntity);
        }

        for(int i=13;i<20;i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("카테고리1상품"+i)
                    .price(1000)
                    .stock(100)
                    .explanation("카테고리1상품설명"+i)
                    .del("N")
                    .sellCnt(0)
                    .likeCnt(0)
                    .goodsCategory(goodsCategoryEntity)
                    .build();
            goodsRepository.save(goodsEntity);

            LikeEntity likeEntity = LikeEntity.builder()
                    .member(memberEntity1)
                    .goods(goodsEntity)
                    .build();
            memberEntity1.addLike(likeEntity);
            goodsEntity.addLike(likeEntity);
            likeRepository.save(likeEntity);
        }

        int page = 0;
        int pageLimit = 7;
        Pageable pageable = PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num"));

        Page<GoodsEntity> goodsEntityPage = likeRepository.findByMemberId(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "num")), memberEntity1.getId());
        Page<GoodsDto> goodsDtos = goodsEntityPage.map
                (goods -> new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice(), goods.getStock(), goods.getGoodsCategory().getCategoryName(),
                        goods.getCreatedTime()));

        System.out.println("**********************");
        List<GoodsDto> goodsDtoList = goodsDtos.getContent();
        for(GoodsDto goodsDto : goodsDtoList){
            System.out.println(goodsDto.getGoodsName());
        }
    }
}