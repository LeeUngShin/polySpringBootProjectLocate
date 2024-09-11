package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.CartItemDto;
import com.example.polySpringBootProject.dto.OrderDetailDto;
import com.example.polySpringBootProject.service.MyService;
import com.example.polySpringBootProject.service.OrderService;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/my")
public class MyController {

    @Autowired
    MyService myService;

    @Autowired
    Utils utils;

    @GetMapping("/orderList")
    public String orderList(Model model, @PageableDefault(page = 1) Pageable pageable,
                            HttpSession session){
        String loginId = (String)session.getAttribute("loginId");
        Page<OrderDetailDto> orderDetailDtoPage = myService.orderList(pageable, loginId);

        int blockLimit = 3;  // 선택 페이지 개수 3개
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < orderDetailDtoPage.getTotalPages()) ? startPage + blockLimit - 1 : orderDetailDtoPage.getTotalPages();  // 3 6 9 12 ~~
        boolean isLast = (startPage + blockLimit-1) >= orderDetailDtoPage.getTotalPages();
        int currentPage = orderDetailDtoPage.getNumber()+1;
        boolean lastPageSet = (startPage + blockLimit-1) >= orderDetailDtoPage.getTotalPages();
        int lastPage = orderDetailDtoPage.getTotalPages();

        model.addAttribute("orderDetailDtoPage", orderDetailDtoPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("blockLimit", blockLimit);
        model.addAttribute("isLast", isLast);
        model.addAttribute("lastPageSet", lastPageSet);
        model.addAttribute("lastPage", lastPage);
        return "my/myOrder";
    }

    @GetMapping("/myOrderDetail/{orderNum}")
    public String myOrderDetail(Model model,
                                @PathVariable("orderNum") Long orderNum,
                                HttpSession session){
        String loginId = (String) session.getAttribute("loginId");
        OrderDetailDto orderDetailDto = myService.orderDetail(orderNum);

        model.addAttribute("orderDetailDto", orderDetailDto);

        return "my/myOrderDetail";
    }

    @PostMapping("/cartInput/{goodsNum}")
    public String cartInput(HttpSession session, CartItemDto cartItemDto, Model model,
                              @PathVariable("goodsNum") Long goodsNum ){
        System.out.println("카트에 넣을 정보 : " + cartItemDto);
        String loginId = (String) session.getAttribute("loginId");
        try{
           boolean cartInput = myService.cartInput(cartItemDto, loginId, goodsNum);
           if(cartInput==false){
               return utils.showMessageAlert("장바구니에 상품을 넣는 도중 에러가 발생했습니다.", "/goods/detail/"+goodsNum, model);
           }

        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return utils.showMessageAlert("데이터를 찾지 못했습니다.", "/goods/detail/"+goodsNum, model);
        }catch (Exception e){
            e.printStackTrace();
            return utils.showMessageAlert("장바구니에 상품을 넣는 도중 에러가 발생했습니다.", "/goods/detail/"+goodsNum, model);
        }

        return "redirect:/my/myCartList";
    }

    @GetMapping("/myCartList")
    public String myCart(HttpSession session, Model model){
        String loginId= (String) session.getAttribute("loginId");


        List<CartItemDto> cartItemDtoList = myService.getCartItemList(loginId);
        System.out.println("aaaaa : " + cartItemDtoList);
        model.addAttribute("cartItemDtoList", cartItemDtoList);

        return "my/myCart";
    }
}