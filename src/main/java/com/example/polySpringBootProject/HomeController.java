package com.example.polySpringBootProject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    /**
     * 메인페이지 반환
     * @param model
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value="/home", method= RequestMethod.GET)
    public String homeView(Model model, HttpServletRequest request, HttpSession session) {

        String id = (String) session.getAttribute("loginId");
        if(id != null && !id.isEmpty()) {
            System.out.println("홈으로 왔더니 현재 세션은 값이 있습니다.");
            return "home";
        }
        System.out.println("홈으로 왔더니 현재 세션값은 없습니다.");

        // String searchId = (String) model.getAttribute("searchId");

        return "home";
    }

}