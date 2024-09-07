package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.dto.MemberDto;
import com.example.polySpringBootProject.dto.OrderDetailDto;
import com.example.polySpringBootProject.dto.OrderDto;
import com.example.polySpringBootProject.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
                            @PathVariable("goodsNum")Long goodsNum,
                            @RequestParam("amount")int amount){
        String loginId = (String) session.getAttribute("loginId");
        try{
            MemberDto memberDto = orderService.loginMemberInfo(loginId);
            GoodsDto goodsDto = orderService.orderGoodsInfo(goodsNum);
            if(goodsDto.getPrice()*amount >=30000){
                System.out.println("배송비 없음");
                model.addAttribute("deliveryPrice", "N");
            }
            else{
                System.out.println("배송비 있음");
                model.addAttribute("deliveryPrice", "Y");
            }
            model.addAttribute("memberDto", memberDto);
            model.addAttribute("goodsDto", goodsDto);
            model.addAttribute("amount", amount);
            model.addAttribute("finalPrice", goodsDto.getPrice()*amount);
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

            return "order/orderResult";
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return utils.showMessageAlert("회원 또는 상품 정보를 찾을 수 없습니다.", "/goods/menu", model);
        }catch (DataAccessException e){
            e.printStackTrace();
            return utils.showMessageAlert("데이터 처리 중 오류가 발생했습니다.", "/goods/detail/"+goodsNum, model);
        }
        catch (RuntimeException e){
            e.printStackTrace();
            return utils.showMessageAlert("주문 처리중 에러가 발생했습니다.", "/goods/detail/"+goodsNum, model);
        }
    }

    @GetMapping("/orderList")
    public String orderList(Model model, @PageableDefault(page = 1) Pageable pageable,
                            HttpSession session){
        String loginId = (String)session.getAttribute("loginId");
        Page<OrderDetailDto> orderDetailDtoPage = orderService.orderList(pageable, loginId);

        return "my/myOrder";
    }

//    @GetMapping("/orderResult")
//    public String orderResult(){
//        return "order/orderResult";
//    }
}
