package com.example.polySpringBootProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Utils {

    public String showMessageAlert(String message,
                                   String redirectUri,
                                   Model model){

        model.addAttribute("message", message);
        model.addAttribute("redirectUri", redirectUri);
        return "jsAlert/alert";
    }

}
