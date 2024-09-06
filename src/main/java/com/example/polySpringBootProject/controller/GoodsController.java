package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.entity.GoodsSubCategoryEntity;
import com.example.polySpringBootProject.repository.GoodsSubCategoryRepository;
import com.example.polySpringBootProject.service.GoodsService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    Utils utils;

    @GetMapping("/menu")
    public String menu(@PageableDefault(page = 1) Pageable pageable,
                       @RequestParam(value="topCategory", defaultValue = "all") String topCategory,
                       @RequestParam(value = "subCategory", defaultValue = "all") String subCategory,
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
                              @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                              HttpSession session,
                              HttpServletRequest request,
                              HttpServletResponse response){
        try {
            GoodsDto goodsDto = goodsService.goodsDetail(goodsNum);
            String goodsMyLike = goodsService.goodsMyLike((String) session.getAttribute("loginId"), goodsNum);
            System.out.println("현재 상품을 내가 찜햇나?? " + goodsMyLike);
            if(goodsMyLike.equals("Y")){
                model.addAttribute("goodsLike", "Y");
            }
            if(goodsMyLike.equals("N")) {
                model.addAttribute("goodsLike", "N");
            }
            System.out.println("찜되어 있는지? " + goodsMyLike);
            model.addAttribute("goodsDto", goodsDto);
            model.addAttribute("page", page);
            model.addAttribute("topCategory", goodsDto.getGoodsCategory());
            model.addAttribute("subCategory", goodsDto.getGoodsSubCategory());
//            Cookie cookie = new Cookie("goodsMyLike", goodsMyLike);
//
//            if (goodsMyLike.equals("Y")) {
//                response.addCookie(cookie);
//            } else {
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }

            return "goods/goodsDetail";
        }
        catch (EntityNotFoundException e){
            e.printStackTrace();
            return utils.showMessageAlert("해당 상품을 찾을 수 없습니다.", "/goods/menu", model);
        }
    }

    @PostMapping("/goodsLike")
    public String goodsLike(HttpServletRequest request){

        String loginId = request.getParameter("loginId");
        String goodsNumStr = request.getParameter("goodsNum");
        Long goodsNum = Long.parseLong(goodsNumStr);
        String likeTFStr = request.getParameter("goodsLikeTF");
        boolean likeTF = Boolean.parseBoolean(likeTFStr);
        System.out.println("찜한 유저 아이디 : " + loginId);
        System.out.println("찜한 상품 번호 : " + goodsNum);
        System.out.println("찜했는지 : " + likeTF);

        try{
            boolean goodsLike = goodsService.goodsLike(loginId, goodsNum, likeTF);
            if(goodsLike){
                System.out.println("찜 추가/취소 성공");
                return "{\"result\" : \"true\"}";
            }else {
                return "{\"result\" : \"false\"}";
            }
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return "{\"result\" : \"fail\"}";

        }catch (RuntimeException e){
            e.printStackTrace();
            return "{\"result\" : \"fail\"}";
        }
        catch (Exception e){
            e.printStackTrace();
            return "{\"result\" : \"fail\"}";
        }
    }



}