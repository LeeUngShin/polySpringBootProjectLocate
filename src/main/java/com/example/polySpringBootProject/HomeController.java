package com.example.polySpringBootProject;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.repository.GoodsRepository;
import com.example.polySpringBootProject.service.GoodsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    GoodsService goodsService;

    /**
     * 메인페이지 반환
     * @param model
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value="/home", method= RequestMethod.GET)
    public String homeView(Model model, HttpServletRequest request, HttpSession session) {

        List<GoodsDto> goodsDtoList = goodsService.getMainBestMenu();
        System.out.println("홈화면 베스트 메뉴 8개 : " + goodsDtoList);
        String id = (String) session.getAttribute("loginId");
        if(id != null && !id.isEmpty()) {
            System.out.println("홈으로 왔더니 현재 세션은 값이 있습니다.");
            model.addAttribute("goodsDtoList", goodsDtoList);
            return "home";
        }
        System.out.println("홈으로 왔더니 현재 세션값은 없습니다.");

        // String searchId = (String) model.getAttribute("searchId");
        model.addAttribute("goodsDtoList", goodsDtoList);
        return "home";
    }

}