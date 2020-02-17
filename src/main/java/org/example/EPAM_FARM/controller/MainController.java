package org.example.EPAM_FARM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public String getIndex (Model model) {
        model.addAttribute("hello","Hello world");
        return "index";
    }

    @GetMapping("/q")
    public String getPage (Model model) {
        return "page";
    }

}
