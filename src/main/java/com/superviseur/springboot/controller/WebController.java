package com.superviseur.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/images/radem_logo.png")
    public String getImage() {
        return "redirect:/images/radem_logo.png";
    }

}
