package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.BoardDto;
import com.example.polySpringBootProject.dto.BoardResponse;
import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.dto.MemberDto;
import com.example.polySpringBootProject.service.AdminService;
import com.example.polySpringBootProject.service.BoardService;
import com.google.gson.Gson;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/noticeBoardForm")
    public String noticeBoardForm(){
        return "/admin/board/noticeBoardWriteForm";
    }

    @PostMapping("/noticeBoardWrite")
    public String noticeBoardWrite(BoardDto boardDto, HttpSession session, Model model){
        String adminId = (String)session.getAttribute("loginId");
        try {
            boolean writeNoticeBoard = adminService.noticeBoardWrite(boardDto, adminId);
            if(adminId == null || adminId.equals("")){
                return utils.showMessageAlert("관리자 계정만 공지글 작성이 가능합니다.", "/admin/noticeBoardForm", model);
            }
            else if (writeNoticeBoard) {
                return utils.showMessageAlert("공지글 작성 완성", "/admin/boardList", model);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return utils.showMessageAlert("공지글 작성 실패", "/admin/noticeBoardForm", model);
    }

    @GetMapping("/boardList")
    public String boardList(@PageableDefault(page=1) Pageable pageable, Model model){

        Page<BoardDto> boardDtoPage = adminService.boardList(pageable);

        int totalLatPage = boardDtoPage.getTotalPages();
        int currentPage = boardDtoPage.getNumber() + 1;
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardDtoPage.getTotalPages()) ? startPage + blockLimit - 1 : boardDtoPage.getTotalPages();  // 3 6 9 12 ~~
        boolean lastPageSet = (startPage + blockLimit - 1) >= boardDtoPage.getTotalPages();

        System.out.println("전체 마지막페이지 : " + totalLatPage);
        System.out.println("마지막 세트인가 : " + lastPageSet);

        model.addAttribute("boardDtoPage", boardDtoPage);
        model.addAttribute("blockLimit", blockLimit);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalLastPage", totalLatPage);
        model.addAttribute("lastPageSet", lastPageSet);

        return "/admin/board/boardList";
    }

    @GetMapping("/boardDetail/{boardNum}")
    @ResponseBody
    public String memberInfo(Model model, @PathVariable("boardNum")Long boardNum){

        try{
            BoardDto boardDto = adminService.boardDetail(boardNum);
            String json = new Gson().toJson(boardDto);
            System.out.println("json : " + json);
            return json;
        }catch (EntityNotFoundException e){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("result", "fail");
            String errorJson = new Gson().toJson(errorMap);
            utils.showMessageAlert("게시글을 찾을 수 없습니다.");
            return errorJson;
        }
    }

    @PostMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("boardNum") Long boardNum, Model model){

        try{

            boolean deleteBoard = adminService.deleteBoard(boardNum);
            if(deleteBoard) return utils.showMessageAlert("삭제가 완료되었습니다.", "/admin/boardList", model);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return utils.showMessageAlert("게시글을 찾을 수 없습니다.", "/admin/boardList", model);
        }catch (RuntimeException e){
            e.printStackTrace();
            return utils.showMessageAlert("게시글 삭제 중 오류가 발생했습니다.", "/admin/boardList", model);
        }
        return utils.showMessageAlert("게시글 삭제 중 오류가 발생했습니다.", "/admin/boardList", model);
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
            else if(goodsRegister.equals("imageUploadFail")){
                return utils.showMessageAlert("이미지업로드에 실패했습니다.", "/admin/goodsRegisterForm", model);
            }
            else{
                return utils.showMessageAlert("상품등록에 실패했습니다.", "/admin/goodsRegisterForm", model);
            }
        }
        catch (DuplicateKeyException e){
            e.printStackTrace();
            return utils.showMessageAlert("상품명은 중복될 수 없습니다..", "/admin/goodsRegisterForm", model);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("컨트롤러에서 예외발생");
            return utils.showMessageAlert("상품 등록에 실패했습니다.", "/admin/goodsRegisterForm", model);
        }
    }

    @GetMapping("/goodsList")
    public String goodsList(@PageableDefault(page=1) Pageable pageable,
                            Model model){

        Page<GoodsDto> goodsDtoPage = adminService.goodsList(pageable);
        int totalLatPage = goodsDtoPage.getTotalPages();
        int currentPage = goodsDtoPage.getNumber() + 1;
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < goodsDtoPage.getTotalPages()) ? startPage + blockLimit - 1 : goodsDtoPage.getTotalPages();  // 3 6 9 12 ~~
        boolean lastPageSet = (startPage + blockLimit - 1) >= goodsDtoPage.getTotalPages();

        System.out.println("전체 마지막페이지 : " + totalLatPage);
        System.out.println("마지막 세트인가 : " + lastPageSet);

        model.addAttribute("goodsDtoPage", goodsDtoPage);
        model.addAttribute("blockLimit", blockLimit);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalLastPage", totalLatPage);
        model.addAttribute("lastPageSet", lastPageSet);

        return "admin/goods/goodsList";
    }

    @GetMapping("/goodsDetail/{goodsNum}")
    public String detailGoods(@PathVariable("goodsNum") Long goodsNum, Model model){

        try{
            GoodsDto goodsDto = adminService.goodsDetail(goodsNum);
            model.addAttribute("goodsDto", goodsDto);
            System.out.println("등록일 : " + goodsDto.getRegTime());
            return "admin/goods/goodsDetail";
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            utils.showMessageAlert("해당 상품을 찾을 수 없습니다.", "/admin/goodsDetail/"+goodsNum, model);
        }
        return "admin/goods/goodsDetail";
    }

    @PostMapping("/deleteGoods/{goodsNum}")
    public String deleteGoods(@PathVariable("goodsNum") Long goodsNum, Model model){
        try{
            boolean deleteGoods = adminService.goodsDelete(goodsNum);
            if(deleteGoods) {
                return utils.showMessageAlert("해당 상품이 삭제되었습니다.", "/admin/goodsList", model);
            }
            else {
                return utils.showMessageAlert("상품 삭제에 실패했습니다.", "/admin/goodsList", model);

            }
        }
        catch (EntityNotFoundException e){
            e.printStackTrace();
            return utils.showMessageAlert("해당 상품을 찾을 수 없습니다.", "/admin/goodsList", model);
        }
        catch (Exception e){
            e.printStackTrace();
            return utils.showMessageAlert("상품 삭제에 실패했습니다.", "/admin/goodsList", model);
        }
    }

    @GetMapping("/modifyGoodsForm/{goodsNum}")
    public String modifyGoodsForm(@PathVariable("goodsNum") Long goodsNum, Model model){

        try{
            GoodsDto goodsDto = adminService.goodsInfo(goodsNum);
            model.addAttribute("goodsDto", goodsDto);
            return "/admin/goods/goodsModifyForm";
        }catch (EntityNotFoundException e){
            return utils.showMessageAlert("해당 상품을 찾을 수 없습니다.", "/admin/goodsList", model);
        }
    }

    @PostMapping("/modifyGoods/{goodsNum}")
    public String modifyGoods(@PathVariable("goodsNum") Long goodNum, Model model,
                              GoodsDto goodsDto){

        try{
            GoodsDto modifyGoodsDto = adminService.goodsModify(goodNum, goodsDto);
            if(modifyGoodsDto != null){
                return utils.showMessageAlert("상품 수정에 성공했습니다.", "/admin/goodsDetail/" + goodNum, model);
            }
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return utils.showMessageAlert("해당 상품을 찾을 수 없습니다.", "/admin/goodsList", model);
        }catch (Exception e){
            e.printStackTrace();
            return utils.showMessageAlert("상품 수정에 실패했습니다.", "/admin/goodsList", model);
        }
        return utils.showMessageAlert("상품 실패했습니다.", "/admin/goodsList", model);
    }
}