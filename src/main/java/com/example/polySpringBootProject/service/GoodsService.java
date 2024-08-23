package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.entity.GoodsSubCategoryEntity;
import com.example.polySpringBootProject.repository.GoodsRepository;
import com.example.polySpringBootProject.repository.GoodsSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Page<GoodsDto> getGoods(Pageable pageable, String topCategory, String subCategory){

        int page = pageable.getPageNumber()-1;
        int pageLimit = 8;

        if(subCategory.equals("all")){
            Page<GoodsEntity> goodsEntityPageTopCategoryAll = goodsRepository.findByGoodsCategoryCategoryName(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "name")), topCategory);
            Page<GoodsDto> goodsDto = goodsEntityPageTopCategoryAll.map
                    (goods -> new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice(), goods.getGoodsImageEntity().getStoredFileNameWithExtension()));
            return goodsDto;

        }else {
            Page<GoodsEntity> goodsEntityPageSubCategory =  goodsRepository.findByGoodsCategoryCategoryName(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "name")), subCategory);
            Page<GoodsDto> goodsDto = goodsEntityPageSubCategory.map
                    (goods -> new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice(), goods.getGoodsImageEntity().getStoredFileNameWithExtension()));
            return goodsDto;
        }
    }

    public List<GoodsDto> getBestGoods(String topCategory, String subCategory){

        if(subCategory.equals("all")){
            List<GoodsEntity> goodsEntityTopCategoryAll = goodsRepository.findByGoodsCategoryCategoryNameTop4OrderBySellCntDescLikeCntDesc(topCategory);
            List<GoodsDto> goodsDtoList = new ArrayList<>();
            for(GoodsEntity goodsEntity : goodsEntityTopCategoryAll){
                GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goodsEntity);
                goodsDtoList.add(goodsDto);
            }
            return goodsDtoList;

        }else {
            List<GoodsEntity> goodsEntitySubCategory =  goodsRepository.findByGoodsSubCategoryCategoryNameTop4OrderBySellCntDescLikeCntDesc(subCategory);
            List<GoodsDto> goodsDtoList = new ArrayList<>();
            for(GoodsEntity goodsEntity : goodsEntitySubCategory){
                GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goodsEntity);
                goodsDtoList.add(goodsDto);
            }
            return goodsDtoList;
        }

    }

    public List<GoodsSubCategoryEntity> getSubCategoryList(){

        return goodsSubCategoryRepository.findAll();

    }
}
