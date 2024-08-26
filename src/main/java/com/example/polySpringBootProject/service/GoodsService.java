package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.entity.GoodsCategoryEntity;
import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.entity.GoodsSubCategoryEntity;
import com.example.polySpringBootProject.repository.GoodsCategoryRepository;
import com.example.polySpringBootProject.repository.GoodsRepository;
import com.example.polySpringBootProject.repository.GoodsSubCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Page<GoodsDto> getGoods(Pageable pageable, String topCategory, String subCategory){

        int page = pageable.getPageNumber()-1;
        int pageLimit = 8;

        Page<GoodsDto> goodsDto;

        if(subCategory.equals("all")){
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

        if(subCategory.equals("all")){
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
}
