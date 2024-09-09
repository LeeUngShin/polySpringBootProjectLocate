package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.OrderDetailDto;
import com.example.polySpringBootProject.service.MyService;
import com.example.polySpringBootProject.service.OrderService;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/my")
public class MyController {

    @Autowired
    MyService myService;

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

        model.addAttribute("orderDetailDtoPage", orderDetailDtoPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("blockLimit", blockLimit);
        model.addAttribute("isLast", isLast);
        return "my/myOrder";
    }

    @GetMapping("/myOrderDetail/{orderNum}")
    public String myOrderDetail(Model model,
                                @PathVariable("orderNum") Long orderNum,
                                HttpSession session){
        String loginId = (String) session.getAttribute("loginId");


        return "my/myOrderDetail";
    }
}
