package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.JoinDto;
import com.example.polySpringBootProject.service.AdminService;
import com.example.polySpringBootProject.service.MemberService;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin")
public class adminController {

    @Autowired
    AdminService adminService;

    @Autowired
    Utils utils;

    @GetMapping("/home")
    public String homeView(HttpSession session, Model model){
        String loginId = (String) session.getAttribute("loginId");
        String role = (String) session.getAttribute("role");
        if(loginId != null && !loginId.isEmpty() && role.equals("ROLE_ADMIN")) {
            System.out.println("관리자홈으로 왔더니 현재 세션은 값이 있습니다.");
            model.addAttribute("loginId", loginId);
            model.addAttribute("role", role);
            return "admin/adminHome";
        }
        else if (loginId != null && !loginId.isEmpty() && !(role.equals("ROLE_ADMIN"))) {
            return utils.showMessageAlert("관리자 계정만 접근가능합니다.", "/home", model);
        }
        else{
            System.out.println("홈으로 왔더니 현재 세션값은 없습니다.");
            return utils.showMessageAlert("관리자 계정만 접근가능합니다.", "/home", model);
        }
        // String searchId = (String) model.getAttribute("searchId");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model){
        adminService.logout(session);
        return utils.showMessageAlert("관리자 계정이 로그아웃되었습니다.", "/home", model);
    }

    @GetMapping("/memberManage")
    public String memberManage(){
        return "/admin/member/memberManage";
    }

    @GetMapping("/memberApproval")
    public String memberApproval(Model model){

        Page<JoinDto> notApprovalMember = adminService.getNotApprovalMember();



    }
}