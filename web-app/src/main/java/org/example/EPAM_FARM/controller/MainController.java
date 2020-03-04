package org.example.EPAM_FARM.controller;

import org.example.EPAM_FARM.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getIndex(
            @AuthenticationPrincipal User user,
                            Model model) {
        model.addAttribute("authUser", user);
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
