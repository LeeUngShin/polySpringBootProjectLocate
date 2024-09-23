package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.entity.*;
import com.example.polySpringBootProject.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsSubCategoryRepository goodsSubCategoryRepository;

    @Autowired
    GoodsCategoryRepository goodsCategoryRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LikeRepository likeRepository;

    public Page<GoodsDto> getGoods(Pageable pageable, String topCategory, String subCategory){

        int page = pageable.getPageNumber()-1;
        int pageLimit = 8;

        Page<GoodsDto> goodsDto;


        if(subCategory.equals("all")&& topCategory.equals("all")) {
            Page<GoodsEntity> goodsEntityPageTopCategoryAll = goodsRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "name")));

            goodsDto = goodsEntityPageTopCategoryAll.map
                    (goods -> {
                        if (goods.getGoodsImageEntity() != null && !goods.getGoodsImageEntity().isEmpty()) {
                            String imageFileName = goods.getGoodsImageEntity().get(0).getStoredFileNameWithExtension();
                            return new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice(), imageFileName);
                        } else {
                            // 비어 있는 경우
                            return new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice());
                        }
                    });
        }
        else if(subCategory.equals("all")){
            Page<GoodsEntity> goodsEntityPageTopCategoryAll = goodsRepository.findByGoodsCategoryCategoryNameOrderByNumDesc(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "name")), topCategory);

            goodsDto = goodsEntityPageTopCategoryAll.map
                    (goods -> {
                        if (goods.getGoodsImageEntity() != null && !goods.getGoodsImageEntity().isEmpty()) {
                            String imageFileName = goods.getGoodsImageEntity().get(0).getStoredFileNameWithExtension();
                            return new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice(), imageFileName);
                        } else {
                            // 비어 있는 경우
                            return new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice());
                        }
                    });
        }else {
            Page<GoodsEntity> goodsEntityPageSubCategory = goodsRepository.findByGoodsSubCategoryCategoryNameOrderByNumDesc(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "name")), subCategory);
            goodsDto = goodsEntityPageSubCategory.map
                    (goods -> {
                        if (goods.getGoodsImageEntity() != null && !goods.getGoodsImageEntity().isEmpty()) {
                            String imageFileName = goods.getGoodsImageEntity().get(0).getStoredFileNameWithExtension();
                            return new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice(), imageFileName);
                        } else {
                            // 비어 있는 경우
                            return new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice());
                        }
                    });
        }
        return goodsDto;
    }

    public List<GoodsDto> getBestGoods(String topCategory, String subCategory){

        if(subCategory.equals("all") && topCategory.equals("all")){
            List<GoodsEntity> goodsEntityTopCategoryAll = goodsRepository.findTop4ByOrderBySellCntDescLikeCntDesc();
            List<GoodsDto> goodsDtoList = new ArrayList<>();
            for(GoodsEntity goodsEntity : goodsEntityTopCategoryAll){
                if(goodsEntity.getGoodsImageEntity().isEmpty() || goodsEntity.getGoodsImageEntity()==null) {
                    GoodsDto goodsDto = GoodsDto.entityToGoodsDtoNotImg(goodsEntity);
                    goodsDtoList.add(goodsDto);

                }
                else{
                    GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goodsEntity);
                    goodsDtoList.add(goodsDto);
                }
            }
            return goodsDtoList;

        }
        else if(subCategory.equals("all")){
            List<GoodsEntity> goodsEntityTopCategoryAll = goodsRepository.findTop4ByGoodsCategoryCategoryNameOrderBySellCntDescLikeCntDesc(topCategory);
            List<GoodsDto> goodsDtoList = new ArrayList<>();
            for(GoodsEntity goodsEntity : goodsEntityTopCategoryAll){
                if(goodsEntity.getGoodsImageEntity().isEmpty() || goodsEntity.getGoodsImageEntity()==null) {
                    GoodsDto goodsDto = GoodsDto.entityToGoodsDtoNotImg(goodsEntity);
                    goodsDtoList.add(goodsDto);

                }
                else{
                    GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goodsEntity);
                    goodsDtoList.add(goodsDto);
                }
            }
            return goodsDtoList;

        }else {
            List<GoodsEntity> goodsEntitySubCategory =  goodsRepository.findTop4ByGoodsSubCategoryCategoryNameOrderBySellCntDescLikeCntDesc(subCategory);
            List<GoodsDto> goodsDtoList = new ArrayList<>();
            for(GoodsEntity goodsEntity : goodsEntitySubCategory){
                if(goodsEntity.getGoodsImageEntity().isEmpty() || goodsEntity.getGoodsImageEntity()==null) {
                    GoodsDto goodsDto = GoodsDto.entityToGoodsDtoNotImg(goodsEntity);
                    goodsDtoList.add(goodsDto);
                }
                else{
                    GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goodsEntity);
                    goodsDtoList.add(goodsDto);
                }
            }
            return goodsDtoList;
        }
    }

    public List<GoodsDto> getMainBestMenu(){

        List<GoodsEntity> goodsEntityList = goodsRepository.findTop8ByOrderBySellCntDescLikeCntDesc();
        List<GoodsDto> goodsDtoList = new ArrayList<>();
        for(GoodsEntity goodsEntity : goodsEntityList){
            if(goodsEntity.getGoodsImageEntity().isEmpty() || goodsEntity.getGoodsImageEntity() == null) {
                GoodsDto goodsDto = GoodsDto.entityToGoodsDtoNotImg(goodsEntity);
                goodsDtoList.add(goodsDto);
            }
            else{
                GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goodsEntity);
                goodsDtoList.add(goodsDto);
            }
        }
        return goodsDtoList;

    }

    public List<GoodsSubCategoryEntity> getSubCategoryList(String topCategory){

        List<GoodsSubCategoryEntity> goodsSubCategoryEntityList = goodsSubCategoryRepository.findByGoodsCategoryEntityCategoryName(topCategory);
        return goodsSubCategoryEntityList;
    }

    public int getCategoryListCnt(){

        List<GoodsCategoryEntity> goodsCategoryEntity = goodsCategoryRepository.findAll();
        int categoryCnt = goodsCategoryEntity.size();
        return categoryCnt;
    }


    public GoodsDto goodsDetail(Long goodsNum){

        GoodsEntity goodsEntity = goodsRepository.findById(goodsNum).orElseThrow(() -> new EntityNotFoundException("해당 상품을 찾을 수 없습니다."));

        if(goodsEntity.getGoodsImageEntity()==null || goodsEntity.getGoodsImageEntity().isEmpty()) {
            GoodsDto goodsDto = GoodsDto.entityToGoodsDtoNotImg(goodsEntity);
            return goodsDto;
        }
        else {
            GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goodsEntity);
            return goodsDto;
        }
    }

    @Transactional
    public boolean goodsLike(String loginId, Long goodsNum, boolean likeTF) {

        MemberEntity memberEntity = memberRepository.findById(loginId).orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다."));
        GoodsEntity goodsEntity = goodsRepository.findById(goodsNum).orElseThrow(() -> new EntityNotFoundException("해당 상품을 찾을 수 없습니다."));
        if (likeTF == true) { // 찜하기
            if (likeRepository.existsByMemberNumAndGoodsNum(memberEntity.getNum(), goodsEntity.getNum())) {
                throw new RuntimeException("이미 찜이 되어 있습니다.");
            } else {  // 찜이 안되어 있으면
                LikeEntity likeEntity = LikeEntity.builder()
                        .member(memberEntity)
                        .goods(goodsEntity)
                        .build();
                LikeEntity savedLikeEntity = likeRepository.save(likeEntity);
                if(savedLikeEntity==null){
                    return false;
                }
                goodsEntity.setLikeCnt(goodsEntity.getLikeCnt() + 1);
                GoodsEntity likeAddGoodsEntity = goodsRepository.save(goodsEntity);
                return true;
            }
        } else {   // 찜 취소하기
            if (!likeRepository.existsByMemberNumAndGoodsNum(memberEntity.getNum(), goodsEntity.getNum())) {
                throw new RuntimeException("찜이 되어있지 않습니다.");
            } else {  // 찜이 되어 있으면
                LikeEntity likeEntity = likeRepository.findByMemberNumAndGoodsNum(memberEntity.getNum(), goodsEntity.getNum()).orElseThrow(() -> new EntityNotFoundException("해당 찜 정보를 찾을 수 없습니다."));
                likeRepository.deleteById(likeEntity.getNum());
                goodsEntity.setLikeCnt(goodsEntity.getLikeCnt()-1);
                GoodsEntity likeSubGoodsEntity = goodsRepository.save(goodsEntity);
                return true;
            }
        }
    }

    public String goodsMyLike(String loginId, Long goodsNum){

        System.out.println("로그인 아이디 : " +loginId);
        System.out.println("상품 번호 : " + goodsNum);
        boolean goodLike = likeRepository.existsByMemberIdAndGoodsNum(loginId, goodsNum);
        System.out.println("찜 정보 있는지 서비스단 : " + goodLike);
        if(goodLike){
            System.out.println("찜 정보 있음");
            return "Y";
        }
        else {
            System.out.println("찜 정보 없음");
            return "N";
        }
    }


    public Page<GoodsDto> searchGoods(Pageable pageable, String keyword){
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 8;

        Page<GoodsEntity> goodsEntityPage = goodsRepository.findByNameContaining(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"num")), keyword);

        Page<GoodsDto> goodsDtos = goodsEntityPage.map
            (goods -> {
                if (goods.getGoodsImageEntity() != null && !goods.getGoodsImageEntity().isEmpty()) {
                    String imageFileName = goods.getGoodsImageEntity().get(0).getStoredFileNameWithExtension();
                    return new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice(), imageFileName);
                } else {
                    // 비어 있는 경우
                    return new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice());
                }
            });
        return goodsDtos;
    }

}
