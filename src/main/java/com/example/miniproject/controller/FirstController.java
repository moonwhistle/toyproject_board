package com.example.miniproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/")
    public String niceToMeetYou(Model model)
    {

        model.addAttribute("username","상휘");

        return "greeting";
    }
}
