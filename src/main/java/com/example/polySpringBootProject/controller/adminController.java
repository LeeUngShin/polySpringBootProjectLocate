package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.dto.MemberDto;
import com.example.polySpringBootProject.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;
import java.util.List;

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

//    @GetMapping("/memberManage")
//    public String memberManage(){
//        return "/admin/member/memberManage";
//    }

    @GetMapping("/memberApproval")
    public String memberApproval(@PageableDefault(page=1) Pageable pageable,
                                     Model model){
        try {
            Page<MemberDto> notApprovalMember = adminService.getNotApprovalMember(pageable);
            System.out.println("현재페이지미승인 회원");
            List<MemberDto> memberDtos = notApprovalMember.getContent();
            System.out.println(memberDtos);

            int totalLatPage = notApprovalMember.getTotalPages();
            int currentPage = notApprovalMember.getNumber()+1;
            int blockLimit = 3;
            int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
            int endPage = ((startPage + blockLimit - 1) < notApprovalMember.getTotalPages()) ? startPage + blockLimit - 1 : notApprovalMember.getTotalPages();  // 3 6 9 12 ~~
            boolean lastPageSet = (startPage + blockLimit-1) >= notApprovalMember.getTotalPages();

            System.out.println("전체 마지막페이지 : " + totalLatPage);
            System.out.println("마지막 세트인가 : " + lastPageSet);


            model.addAttribute("notApprovalMember", notApprovalMember);
            model.addAttribute("blockLimit", blockLimit);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalLastPage", totalLatPage);
            model.addAttribute("lastPageSet", lastPageSet);

            return "admin/member/memberApproval";
        }
        catch (Exception e){
            e.printStackTrace();
            return "/admin/member/memberManage";
        }
    }

    @PostMapping("/memberApprovalComplete")
    public String memberApprovalComplete(HttpServletRequest request, Model model){

        String choiceId = request.getParameter("id");
        try {
            boolean approvalComplete = adminService.memberApproval(choiceId);
            if(approvalComplete){
                return utils.showMessageAlert(choiceId+"님의 회원 승인이 완료되었습니다.", "/admin/memberApproval", model);
            }
            else{
                return utils.showMessageAlert(choiceId+"님의 회원 승인이 실패했습니다.", "/admin/memberApproval", model);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return utils.showMessageAlert(choiceId+"님의 회원 승인이 실패했습니다.", "/admin/memberApproval", model);
        }
    }

    @GetMapping("/memberList")
    public String memberList(@PageableDefault(page=1) Pageable pageable, Model model){
        try {
            Page<MemberDto> memberList = adminService.getMemberList(pageable);
            System.out.println("회원목록");
            List<MemberDto> memberDtos = memberList.getContent();
            System.out.println(memberDtos);

            int totalLatPage = memberList.getTotalPages();
            int currentPage = memberList.getNumber() + 1;
            int blockLimit = 3;
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
            int endPage = ((startPage + blockLimit - 1) < memberList.getTotalPages()) ? startPage + blockLimit - 1 : memberList.getTotalPages();  // 3 6 9 12 ~~
            boolean lastPageSet = (startPage + blockLimit - 1) >= memberList.getTotalPages();

            System.out.println("전체 마지막페이지 : " + totalLatPage);
            System.out.println("마지막 세트인가 : " + lastPageSet);

            model.addAttribute("notApprovalMember", memberList);
            model.addAttribute("blockLimit", blockLimit);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalLastPage", totalLatPage);
            model.addAttribute("lastPageSet", lastPageSet);

            return "admin/member/memberList";
        }catch (Exception e){
            e.printStackTrace();
            return utils.showMessageAlert("회원목록 조회에 실패했습니다.", "/admin/memberManage", model);
        }
    }


    @GetMapping("/goodsRegisterForm")
    public String goodsRegisterForm(){

        return "admin/goods/goodsRegister";
    }

    @PostMapping("/goodsRegister")
    public String goodsRegister(@ModelAttribute GoodsDto goodsDto, Model model){

        try {
            String goodsRegister = adminService.goodsRegister(goodsDto);
            System.out.println("반환받은 문자열 : " + goodsRegister);
            if (goodsRegister.equals("success")) {
                return utils.showMessageAlert("상품 등록에 성공했습니다.", "/admin/goodsRegisterForm", model);
            }
            else if(goodsRegister.equals("notFoundCategory")){
                return utils.showMessageAlert("카테고리를 찾는데 실패했습니다.", "/admin/goodsRegisterForm", model);
            }
            else if(goodsRegister.equals("imageUploadFail")){
                return utils.showMessageAlert("이미지업로드에 실패했습니다.", "/admin/goodsRegisterForm", model);
            }
            else if(goodsRegister.equals("duplicateName")){
                return utils.showMessageAlert("상품명은 중복될 수 없습니다..", "/admin/goodsRegisterForm", model);
            }
            else if(goodsRegister.equals("DBFail")){
                return utils.showMessageAlert("DB관련 오류때문에 실패했습니다.", "/admin/goodsRegisterForm", model);
            }
            else{
                return utils.showMessageAlert("상품등록에 실패했습니다.", "/admin/goodsRegisterForm", model);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("컨트롤러에서 예외발생");
            return utils.showMessageAlert("컨트롤러 예외로 상품 등록에 실패했습니다.", "/admin/goodsRegisterForm", model);
        }
    }
}