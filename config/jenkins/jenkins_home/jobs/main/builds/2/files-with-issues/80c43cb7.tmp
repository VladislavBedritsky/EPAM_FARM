package org.example.EPAM_FARM.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${spring.url}")
    private String url;

    @GetMapping
    public String getIndex (Model model) {
        model.addAttribute("hello","Hello world");
        model.addAttribute("url", url);
        return "index";
    }

    @GetMapping("/q")
    public String getPage (Model model) {
        return "page";
    }
}
