package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.enumClass.MemberGrade;
import com.example.polySpringBootProject.enumClass.RoleType;
import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.entity.*;
import com.example.polySpringBootProject.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GoodsServiceTest {

    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsCategoryRepository goodsCategoryRepository;
    @Autowired
    GoodsSubCategoryRepository goodsSubCategoryRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    GoodsImageRepository goodsImageRepository;


    @Test
    void categorySave() {

        String[] topCategory = {"빵", "케이크", "디저트", "음료"};

        for (int i = 0; i < topCategory.length; i++) {
            GoodsCategoryEntity categoryEntity = GoodsCategoryEntity.builder()
                    .categoryName(topCategory[i])
                    .build();
            goodsCategoryRepository.save(categoryEntity);
        }

        String[] subCategory1 = {"식빵", "건강빵", "도넛", "파이", "카스테라"};
        String[] subCategory2 = {"생크림케이크", "티라미수", "치즈케이크", "조각케이크", "캐릭터케이크", "선물용케이크"};
        String[] subCategory3 = {"마카롱", "아이스크림", "초콜릿", "잼", "쿠키"};
        String[] subCategory4 = {"커피", "밀크티", "스무디", "빙수", "우유", "완제음료"};

        for (int i = 0; i < subCategory1.length; i++) {
            GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(1L).get();
            GoodsSubCategoryEntity goodsSubCategoryEntity = GoodsSubCategoryEntity.builder()
                    .categoryName(subCategory1[i])
                    .goodsCategoryEntity(goodsCategoryEntity)
                    .build();
            goodsSubCategoryRepository.save(goodsSubCategoryEntity);
        }
        for (int i = 0; i < subCategory2.length; i++) {
            GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(2L).get();
            GoodsSubCategoryEntity goodsSubCategoryEntity = GoodsSubCategoryEntity.builder()
                    .categoryName(subCategory2[i])
                    .goodsCategoryEntity(goodsCategoryEntity)
                    .build();
            goodsSubCategoryRepository.save(goodsSubCategoryEntity);
        }
        for (int i = 0; i < subCategory3.length; i++) {
            GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(3L).get();
            GoodsSubCategoryEntity goodsSubCategoryEntity = GoodsSubCategoryEntity.builder()
                    .categoryName(subCategory3[i])
                    .goodsCategoryEntity(goodsCategoryEntity)
                    .build();
            goodsSubCategoryRepository.save(goodsSubCategoryEntity);
        }
        for (int i = 0; i < subCategory4.length; i++) {
            GoodsCategoryEntity goodsCategoryEntity = goodsCategoryRepository.findById(4L).get();
            GoodsSubCategoryEntity goodsSubCategoryEntity = GoodsSubCategoryEntity.builder()
                    .categoryName(subCategory4[i])
                    .goodsCategoryEntity(goodsCategoryEntity)
                    .build();
            goodsSubCategoryRepository.save(goodsSubCategoryEntity);
        }
    }

    @Test
    void goodsInsert(){
        GoodsCategoryEntity goodsCategoryEntity1 = goodsCategoryRepository.findById(1L).get();
        GoodsSubCategoryEntity subCategoryEntity1 = goodsSubCategoryRepository.findByCategoryName("식빵").get();
        GoodsSubCategoryEntity subCategoryEntity2 = goodsSubCategoryRepository.findByCategoryName("도넛").get();
        //GoodsImageEntity goodsImageEntity = goodsImageRepository.findById(1L).get();
        List<String> alleryList = new ArrayList<>();
        alleryList.add("대두");
        alleryList.add("밀");
        for (int i = 0; i < 13; i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("식빵" + i)
                    .price(1000)
                    .stock(10)
                    .explanation("식빵설명" + i)
                    .del("N")
                    .kcal(10)
                    .protein(21)
                    .fat(15)
                    .natrium(12)
                    .sugar(5)
                    .weight(85)
                    .sellCnt(0)
                    .likeCnt(0)
                    .allergy(alleryList)
                    .goodsCategory(goodsCategoryEntity1)
                    .goodsSubCategory(subCategoryEntity1)
                    //.goodsImageEntity(goodsImageEntity)
                    .build();
            goodsRepository.save(goodsEntity);
        }
        for (int i = 14; i < 16; i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("식빵" + i)
                    .price(1000)
                    .stock(10)
                    .explanation("식빵설명" + i)
                    .del("N")
                    .kcal(10)
                    .protein(21)
                    .fat(15)
                    .natrium(12)
                    .sugar(5)
                    .weight(85)
                    .sellCnt(0)
                    .likeCnt(0)
                    .allergy(alleryList)
                    .goodsCategory(goodsCategoryEntity1)
                    .goodsSubCategory(subCategoryEntity1)
                    //.goodsImageEntity(goodsImageEntity)
                    .build();
            goodsRepository.save(goodsEntity);
        }

        for (int i = 17; i < 20; i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("식빵" + i)
                    .price(1000)
                    .stock(10)
                    .explanation("식빵설명" + i)
                    .del("N")
                    .kcal(10)
                    .protein(21)
                    .fat(15)
                    .natrium(12)
                    .sugar(5)
                    .weight(85)
                    .sellCnt(0)
                    .likeCnt(0)
                    .allergy(alleryList)
                    .goodsCategory(goodsCategoryEntity1)
                    .goodsSubCategory(subCategoryEntity1)
                    //.goodsImageEntity(goodsImageEntity)
                    .build();
            goodsRepository.save(goodsEntity);
        }

        // 13 판매개수 15  / 찜 0
        GoodsEntity goodsEntity13 = GoodsEntity.builder()
                .name("식빵13")
                .price(1000)
                .stock(10)
                .explanation("식빵13")
                .del("N")
                .kcal(10)
                .protein(21)
                .fat(15)
                .natrium(12)
                .sugar(5)
                .weight(85)
                .sellCnt(15)
                .likeCnt(0)
                .allergy(alleryList)
                .goodsCategory(goodsCategoryEntity1)
                .goodsSubCategory(subCategoryEntity1)
                //.goodsImageEntity(goodsImageEntity)
                .build();
        goodsRepository.save(goodsEntity13);

        // 16 판매개수 5 / 찜 3
        GoodsEntity goodsEntity16 = GoodsEntity.builder()
                .name("식빵16")
                .price(1000)
                .stock(10)
                .explanation("식빵16")
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
                //.goodsImageEntity(goodsImageEntity)
                .build();
        goodsRepository.save(goodsEntity16);

        for (int i = 20; i < 25; i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("도넛" + i)
                    .price(1000)
                    .stock(10)
                    .explanation("도넛" + i)
                    .del("N")
                    .kcal(10)
                    .protein(21)
                    .fat(15)
                    .natrium(12)
                    .sugar(5)
                    .weight(85)
                    .sellCnt(0)
                    .likeCnt(0)
                    .allergy(alleryList)
                    .goodsCategory(goodsCategoryEntity1)
                    .goodsSubCategory(subCategoryEntity2)
                    //.goodsImageEntity(goodsImageEntity)
                    .build();
            goodsRepository.save(goodsEntity);
        }
        for (int i = 26; i < 30; i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("도넛" + i)
                    .price(1000)
                    .stock(10)
                    .explanation("도넛" + i)
                    .del("N")
                    .kcal(10)
                    .protein(21)
                    .fat(15)
                    .natrium(12)
                    .sugar(5)
                    .weight(85)
                    .sellCnt(0)
                    .likeCnt(0)
                    .allergy(alleryList)
                    .goodsCategory(goodsCategoryEntity1)
                    .goodsSubCategory(subCategoryEntity2)
                    //.goodsImageEntity(goodsImageEntity)
                    .build();
            goodsRepository.save(goodsEntity);
        }
        for (int i = 31; i < 35; i++) {
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name("도넛" + i)
                    .price(1000)
                    .stock(10)
                    .explanation("도넛" + i)
                    .del("N")
                    .kcal(10)
                    .protein(21)
                    .fat(15)
                    .natrium(12)
                    .sugar(5)
                    .weight(85)
                    .sellCnt(0)
                    .likeCnt(0)
                    .allergy(alleryList)
                    .goodsCategory(goodsCategoryEntity1)
                    .goodsSubCategory(subCategoryEntity2)
                    //.goodsImageEntity(goodsImageEntity)
                    .build();
            goodsRepository.save(goodsEntity);
        }

        //25 판매개수 2
        GoodsEntity goodsEntity25 = GoodsEntity.builder()
                .name("도넛25")
                .price(1000)
                .stock(10)
                .explanation("도넛25")
                .del("N")
                .kcal(10)
                .protein(21)
                .fat(15)
                .natrium(12)
                .sugar(5)
                .weight(85)
                .sellCnt(2)
                .likeCnt(0)
                .allergy(alleryList)
                .goodsCategory(goodsCategoryEntity1)
                .goodsSubCategory(subCategoryEntity2)
                //.goodsImageEntity(goodsImageEntity)
                .build();
        goodsRepository.save(goodsEntity25);

        // 30 판매개수 5 / 찜 10
        GoodsEntity goodsEntity30 = GoodsEntity.builder()
                .name("도넛30")
                .price(1000)
                .stock(10)
                .explanation("도넛30")
                .del("N")
                .kcal(10)
                .protein(21)
                .fat(15)
                .natrium(12)
                .sugar(5)
                .weight(85)
                .sellCnt(5)
                .likeCnt(10)
                .allergy(alleryList)
                .goodsCategory(goodsCategoryEntity1)
                .goodsSubCategory(subCategoryEntity2)
                //.goodsImageEntity(goodsImageEntity)
                .build();
        goodsRepository.save(goodsEntity30);
    }

    @Test
    void getGoods() {
        MemberEntity memberEntity1 = MemberEntity.builder()
                .id("admin")
                .pw("12345")
                .name("관리자")
                .post("00000")
                .addr("주소01")
                .addrDetail("상세주소01")
                .email("admin@test.org")
                .approval("Y")
                .role(RoleType.ROLE_USER)
                .grade(MemberGrade.BRONZE)
                .build();
        MemberEntity saveMember1 = memberRepository.save(memberEntity1);

        goodsInsert();

        int page = 0;  // 현재페이지
        int pageLimit = 7;  // 한페이지에 보여줄 상품 개수

        Pageable pageable = PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num"));

        System.out.println(goodsCategoryRepository.findById(1L));

//        assertEquals(62, goodsRepository.findAll().size());
//        assertEquals(35, goodsRepository.findByGoodsCategoryCategoryName("빵").size());
//        assertEquals(27, goodsRepository.findByGoodsCategoryCategoryName("케이크").size());
//        assertEquals(20, goodsRepository.findByGoodsSubCategoryCategoryName("식빵").size());
//        assertEquals(15, goodsRepository.findByGoodsSubCategoryCategoryName("도넛").size());
//        assertEquals(14, goodsRepository.findByGoodsSubCategoryCategoryName("생크림케이크").size());
//        assertEquals(13, goodsRepository.findByGoodsSubCategoryCategoryName("치즈케이크").size());
//        assertEquals(7, goodsRepository.findByGoodsCategoryCategoryName(pageable, "빵").getSize());
//        assertEquals(7, goodsRepository.findByGoodsCategoryCategoryName(pageable, "케이크").getSize());
//        assertEquals(7, goodsRepository.findByGoodsSubCategoryCategoryName(pageable, "식빵").getSize());
//        assertEquals(7, goodsRepository.findByGoodsSubCategoryCategoryName(pageable, "도넛").getSize());
//        assertEquals(7, goodsRepository.findByGoodsSubCategoryCategoryName(pageable, "생크림케이크").getSize());
//        assertEquals(7, goodsRepository.findByGoodsSubCategoryCategoryName(pageable, "치즈케이크").getSize());
    }

    @Test
    void getBestGoods() {
        MemberEntity memberEntity1 = MemberEntity.builder()
                .id("admin")
                .pw("12345")
                .name("관리자")
                .post("00000")
                .addr("주소01")
                .addrDetail("상세주소01")
                .email("admin@test.org")
                .approval("Y")
                .role(RoleType.ROLE_USER)
                .grade(MemberGrade.BRONZE)
                .build();
        memberRepository.save(memberEntity1);

        goodsInsert();

        List<GoodsDto> goodsEntityListTop4 = goodsService.getBestGoods("빵", "all");
        System.out.println(goodsEntityListTop4);
        assertEquals("식빵13", goodsEntityListTop4.get(0).getGoodsName());
        assertEquals("식빵16", goodsEntityListTop4.get(2).getGoodsName());
        assertEquals("도넛25", goodsEntityListTop4.get(3).getGoodsName());
        assertEquals("도넛30", goodsEntityListTop4.get(1).getGoodsName());


    }


    @Test
    void goodsDetail(){
        MemberEntity memberEntity1 = MemberEntity.builder()
                .id("admin")
                .pw("12345")
                .name("관리자")
                .post("00000")
                .addr("주소01")
                .addrDetail("상세주소01")
                .email("admin@test.org")
                .approval("Y")
                .role(RoleType.ROLE_USER)
                .grade(MemberGrade.BRONZE)
                .build();
        memberRepository.save(memberEntity1);

        goodsInsert();

        //GoodsDto goodsDto = goodsService.goodsDetail("도넛22");
        //System.out.println(goodsDto);
        //assertEquals("도넛22", goodsDto.getGoodsName());
    }
    @Test
    //@Transactional
    void a(){
        /*MemberEntity memberEntity1 = MemberEntity.builder()
                .id("testUser")
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
        memberRepository.save(memberEntity1);*/
        GoodsCategoryEntity goodsCategoryEntity1 = goodsCategoryRepository.findById(1L).get();
        GoodsSubCategoryEntity subCategoryEntity1 = goodsSubCategoryRepository.findByCategoryName("식빵").get();
        GoodsSubCategoryEntity subCategoryEntity2 = goodsSubCategoryRepository.findByCategoryName("도넛").get();
        GoodsImageEntity goodsImageEntity = goodsImageRepository.findById(5L).get();
        List<String> alleryList = new ArrayList<>();
        alleryList.add("대두");
        alleryList.add("밀");
        GoodsEntity goodsEntity16 = GoodsEntity.builder()
                .name("식빵27")
                .price(1000)
                .stock(10)
                .explanation("식빵27")
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
        GoodsEntity savedGoodsEntity = goodsRepository.save(goodsEntity16);

        System.out.println("저장한 엔티티 : " + savedGoodsEntity);
        //System.out.println("알러지 추가 : " + savedGoodsEntity);
        //System.out.println("엔티티 : " + goodsRepository.findById(338L).get());
    }

    @Test
    void b(){
        System.out.println("엔티티 : " + goodsRepository.findById(1L).get().getAllergy());
        System.out.println("엔티티 알러지 : " + goodsRepository.findById(1L).get().getAllergy().size());
        System.out.println("엔티티 타입" + goodsRepository.findById(1L).get().getAllergy().getClass().getName());
    }

    @Test
    void goodsLike() {

//        MemberEntity memberEntity1 = MemberEntity.builder()
//                .id("testUser")
//                .pw("12345")
//                .name("테스트유저")
//                .post("00000")
//                .addr("주소01")
//                .addrDetail("상세주소01")
//                .email("testUser@test.org")
//                .approval("Y")
//                .role(RoleType.ROLE_USER)
//                .grade(MemberGrade.BRONZE)
//                .build();
//        MemberEntity saveMember1 = memberRepository.save(memberEntity1);

//        GoodsCategoryEntity goodsCategoryEntity1 = goodsCategoryRepository.findById(1L).get();
//        GoodsSubCategoryEntity subCategoryEntity1 = goodsSubCategoryRepository.findByCategoryName("식빵").get();
//        List<String> alleryList = new ArrayList<>();
//        GoodsEntity goodsEntity16 = GoodsEntity.builder()
//                .name("테스트상품")
//                .price(1000)
//                .stock(10)
//                .explanation("식빵27")
//                .del("N")
//                .kcal(10)
//                .protein(21)
//                .fat(15)
//                .natrium(12)
//                .sugar(5)
//                .weight(85)
//                .sellCnt(0)
//                .likeCnt(0)
//                .allergy(alleryList)
//                .goodsCategory(goodsCategoryEntity1)
//                .goodsSubCategory(subCategoryEntity1)
//                .build();
//        GoodsEntity savedGoodsEntity = goodsRepository.save(goodsEntity16);


        MemberEntity saveMember1 = memberRepository.findById("testUser").get();
        GoodsEntity savedGoodsEntity = goodsRepository.findById(45L).get();
        // 찜하기
        System.out.println("초기 찜개수 : " + savedGoodsEntity.getLikeCnt());
        goodsService.goodsLike(saveMember1.getId(), savedGoodsEntity.getNum(), true);
        GoodsEntity likeAddGoodEnitity = goodsRepository.findById(savedGoodsEntity.getNum()).get();
        System.out.println("찜한 후 찜개수: " + likeAddGoodEnitity.getLikeCnt());
    }
}