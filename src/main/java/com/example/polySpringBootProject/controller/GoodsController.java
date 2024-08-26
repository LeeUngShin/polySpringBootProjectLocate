package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.entity.GoodsSubCategoryEntity;
import com.example.polySpringBootProject.repository.GoodsSubCategoryRepository;
import com.example.polySpringBootProject.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/menu")
    public String menu(@PageableDefault(page = 1) Pageable pageable,
                       @RequestParam("topCategory") String topCategory,
                       @RequestParam("subCategory") String subCategory,
                       Model model) {

        Page<GoodsDto> goodsDtoPage = goodsService.getGoods(pageable, topCategory, subCategory);
        int currentPage = goodsDtoPage.getNumber() + 1;
        int blockLimit = 3;  // 선택 페이지 개수 3개
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < goodsDtoPage.getTotalPages()) ? startPage + blockLimit - 1 : goodsDtoPage.getTotalPages();  // 3 6 9 12 ~~
        boolean isLast = (startPage + blockLimit - 1) >= goodsDtoPage.getTotalPages();
        int totalPage = goodsDtoPage.getTotalPages();
        //int goodCategoryCnt = goodsService.getCategoryListCnt();
        List<GoodsSubCategoryEntity> goodsSubCategoryEntityList = goodsService.getSubCategoryList(topCategory);
        List<GoodsDto> bestGoodsDtoList = goodsService.getBestGoods(topCategory, subCategory);
        System.out.println("현재 페이지 상품 : " + currentPage);
        System.out.println("마지막페이지 : " + totalPage);

        System.out.println("페이징 :" + goodsDtoPage);
        System.out.println("페이징을 리스트로 " + goodsDtoPage.getContent());
        System.out.println("!!!전체 상품 이미지!!!");
        for (GoodsDto goodsDto : goodsDtoPage.getContent()) {
            System.out.println(goodsDto.getStoredGoodsImageName());
        }

        model.addAttribute("bestGoodsDtoList", bestGoodsDtoList);  // 해당 카테고리 베스트메뉴4개
        model.addAttribute("goodsDtoPage", goodsDtoPage);  // 해당 카테고리 메뉴
        model.addAttribute("startPage", startPage);  // 3개 숫자중 첫번째 숫자
        model.addAttribute("endPage", endPage);  // 3개 숫자중 마지막 숫자
        model.addAttribute("currentPage", currentPage);  // 현재페이지
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("blockLimit", blockLimit);  // 페이지 선택할수 있는 개수
        model.addAttribute("isLast", isLast);  // 이 페이지가 마지막 세트인가
        model.addAttribute("topCategory", topCategory);
        model.addAttribute("subCategory", subCategory);
        model.addAttribute("goodsSubCategoryEntityList", goodsSubCategoryEntityList);
        return "goods/goodsMenu";
    }

    @GetMapping("/detail/{goodsNum}")
    public String goodsDetail(@PathVariable("goodsNum") Long goodsNum,
                              Model model,
                              @RequestParam("page") int page){

        GoodsDto goodsDto = goodsService.goodsDetail(goodsNum);
        model.addAttribute("goodsDto", goodsDto);
        model.addAttribute("page", page);
        model.addAttribute("topCategory", goodsDto.getGoodsCategory());
        model.addAttribute("subCategory", goodsDto.getGoodsSubCategory());

        return "goods/goodsDetail";
    }



}