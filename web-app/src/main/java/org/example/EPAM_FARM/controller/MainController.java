package org.example.EPAM_FARM.controller;

import org.example.EPAM_FARM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getIndex(Model model) {
        userService.checkIfUserIsNotAnonymous(model);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/accessdenied")
    public String accessdenied() {
        return "denied";
    }
}
