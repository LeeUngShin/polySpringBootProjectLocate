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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/menu")
    public String menu(@PageableDefault(page=1) Pageable pageable,
                        @RequestParam("topCategory")String topCategory,
                       @RequestParam("subCategory")String subCategory,
                       Model model){

        Page<GoodsDto> goodsEntityPage = goodsService.getGoods(pageable, topCategory, subCategory);
        int currentPage = goodsEntityPage.getNumber()+1;
        int blockLimit = 3;  // 선택 페이지 개수 3개
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < goodsEntityPage.getTotalPages()) ? startPage + blockLimit - 1 : goodsEntityPage.getTotalPages();  // 3 6 9 12 ~~
        boolean isLast = (startPage + blockLimit-1) >= goodsEntityPage.getTotalPages();

        List<GoodsSubCategoryEntity> goodsSubCategoryEntityList = goodsService.getSubCategoryList();
        List<GoodsDto> goodsDtoList = goodsService.getBestGoods(topCategory, subCategory);


        model.addAttribute("boardList", goodsEntityPage);
        model.addAttribute("startPage", startPage);  // 3개 숫자중 첫번째 숫자
        model.addAttribute("endPage", endPage);  // 3개 숫자중 마지막 숫자
        model.addAttribute("currentPage", currentPage);  // 현재페이지
        model.addAttribute("blockLimit", blockLimit);  // 페이지 선택할수 있는 개수
        model.addAttribute("isLast", isLast);  // 이 페이지가 마지막 세트인가
        model.addAttribute("topCategory", topCategory);
        model.addAttribute("subCategory", subCategory);
        model.addAttribute("goodsSubCategoryEntityList", goodsSubCategoryEntityList);
        return "goods/goodsMenu";

    }


}
