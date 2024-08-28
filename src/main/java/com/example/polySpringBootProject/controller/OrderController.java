package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.MemberDto;
import com.example.polySpringBootProject.dto.OrderDetailDto;
import com.example.polySpringBootProject.dto.OrderDto;
import com.example.polySpringBootProject.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    Utils utils;

    @GetMapping("/orderForm/{goodsNum}")
    public String orderForm(HttpSession session, Model model,
                            @PathVariable("goodsNum")Long goodsNum){
        String loginId = (String) session.getAttribute("loginId");
        try{
            MemberDto memberDto = orderService.loginMemberInfo(loginId);
            model.addAttribute("memberDto", memberDto);

            return "order/orderForm";
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return utils.showMessageAlert("회원만 상품구매가 가능합니다.", "/goods/detail/"+goodsNum, model);
        }catch (Exception e){
            e.printStackTrace();
            return utils.showMessageAlert("주문창을 불러오지 못했습니다.", "/goods/detail/"+goodsNum, model);
        }
    }

    @PostMapping("/{goodsNum}")
    public String orderProcess(@ModelAttribute OrderDto orderDto, Model model,
                               @PathVariable("goodsNum")Long goodsNum,
                               HttpSession session){

        String loginId = (String)session.getAttribute("loginId");
        try {
            OrderDetailDto savedOrderDetailDto = orderService.orderComplete(orderDto, loginId, goodsNum);

            model.addAttribute("savedOrderDetailDto", savedOrderDetailDto);

            return "my/orderComplete";
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return utils.showMessageAlert("회원 또는 상품 정보를 찾을 수 없습니다.", "/goods/menu", model);
        }catch (RuntimeException e){
            e.printStackTrace();
            return utils.showMessageAlert("주문 처리중 에러가 발생했습니다.", "/goods/detail/"+goodsNum, model);
        }
    }
}
