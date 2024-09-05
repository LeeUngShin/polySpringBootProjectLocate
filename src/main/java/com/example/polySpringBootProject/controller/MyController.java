package com.example.polySpringBootProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {

    @GetMapping("/myOrder")
    public String myOrder(){

        return "my/myOrder";
    }

    @GetMapping("/myOrderDetail")
    public String myOrderDetail(){

        return "my/myOrderDetail";
    }
}
