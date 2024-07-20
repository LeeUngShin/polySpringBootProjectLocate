package com.example.polySpringBootProject.controller;

import com.example.polySpringBootProject.dto.JoinDto;
import com.example.polySpringBootProject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

/*	public MemberController(MemberService memberService){  // 생성자 자동 주입
		this.memberService = memberService;
	}*/

    /**
     * 회원가입 화면 반환
     * @param request
     * @return
     */
    @RequestMapping(value="/join", method=RequestMethod.GET)
    public String joinView(HttpServletRequest request) {

        return "member/join";
    }

    /**
     * 회원가입 처리
     * @param request
     * @param joinForm
     * @return
     */
    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String joinProcess(HttpServletRequest request, @ModelAttribute JoinDto joinForm) {  // joinForm의 변수명과 회원가입 템플릿의 name값은 동일해야 함

        boolean joinSuccess = memberService.join(joinForm);
        if(joinSuccess) {
            System.out.println("회원가입 성공");
            return "redirect:/home";
        }
        else {
            System.out.println("회원가입 실패");
            request.setAttribute("inputId", joinForm.getId());
            return "member/join";
        }
    }

    /**
     * 로그인 화면 반환
     * @param request
     * @return
     */
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginView(HttpServletRequest request) {
        System.out.println("회원가입 후 여기로 옴");
        return "member/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginProcess(HttpServletRequest request, HttpSession session) {

        String id =request.getParameter("id");
        String pw =request.getParameter("pw");
        boolean loginSuccess = memberService.login(id, pw, session);

        if(loginSuccess) {
            log.info("로그인 했더니 현재 세션 데이터" + session.getAttribute("member"));
            return "redirect:/home";
        }
        else {
            System.out.println("로그인 실패");
            return "member/login";
        }
    }

    /**
     * 로그아웃 처리
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logoutProcess(HttpServletRequest request, HttpSession session) {

        memberService.logout(session);

        return "redirect:/home";
    }

    /**
     * 회원탈퇴 페이지 출력
     * @return
     */
    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public String deleteForm(HttpServletRequest request) {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        System.out.println("AAAAA : "+ id);
        return "member/delete";
    }

    /**
     * 회원탈퇴 처리
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String deleteProcess(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        String pw = request.getParameter("pw");
        String id = request.getParameter("id");
        System.out.println("탈퇴하려는 id, pw : " + id + "   , " + pw);
        if(memberService.delete(id, pw, session)) {
            System.out.println("회원탈퇴 성공");
            return "redirect:/home";
        }
        else {
            System.out.println("회원탈퇴 실패");
            request.setAttribute("id", id);
            //redirectAttributes.addFlashAttribute("dataToSend", id);
            //return "member/delete";
            return "redirect:/member/delete" + "?id=" + id;
            //return "rediret:/member/delete";
        }
    }

    /**
     * 회원정보 수정 페이지 출력
     * @return
     */
    @RequestMapping(value="/modify", method=RequestMethod.GET)
    public String loginView(HttpServletRequest request, HttpSession session) {

        String id = (String) session.getAttribute("loginId");  // 로그인한 아이디
        JoinDto joinForm = memberService.modifyForm(id);
        request.setAttribute("member", joinForm);

        return "member/myPage";
    }

    @RequestMapping(value="/modify", method=RequestMethod.POST)
    public String modifyProcess(HttpServletRequest request, @ModelAttribute JoinDto joinForm) {

        boolean modify = memberService.modify(joinForm);

        if(modify) {
            //return "redirect:/home";
            return "redirect:/member/detail/" + joinForm.getId();
        }

        return "member/modify";
    }

    /**
     * 아이디 찾기 화면 출력
     * @return
     */
    @RequestMapping(value="/searchId", method=RequestMethod.GET)
    public String searchIdView() {
        return "member/searchId";
    }

    @RequestMapping(value="/searchId", method=RequestMethod.POST)
    public String searchId(HttpServletRequest request, Model model) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        String id = memberService.searchId(name, email);
        model.addAttribute("searchId", id);

        return "member/searchId";
    }

    @RequestMapping(value="/searchPw", method=RequestMethod.GET)
    public String searchPwView() {
        return "member/searchPw";
    }

    @RequestMapping(value="/searchPw", method=RequestMethod.POST)
    public String searchPw(HttpServletRequest request, Model model) {

        String id = request.getParameter("id");

        String pw = memberService.searchPw(id);
        model.addAttribute("searchPw", pw);

        return "member/searchPw";
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String searchPw(HttpServletRequest request){

        List<JoinDto> list = memberService.findAll();
        request.setAttribute("memberList", list);
        System.out.println("aa : "+ list);
        return "member/list";
    }

    @RequestMapping(value="/detail/{currentId}", method= RequestMethod.GET)
    public String detail(HttpServletRequest request, @PathVariable("currentId") String id) {

        JoinDto joinForm = memberService.findById(id);

        if(joinForm != null) {

            request.setAttribute("member", joinForm);
            log.info("조회할 유저 : " + joinForm);
            return "member/detail";
        }
        else {
            return "redirect:/member/list";
        }
    }

}