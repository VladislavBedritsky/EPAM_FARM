package org.example.EPAM_FARM.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getIndex(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals("anonymousUser")) {
            model.addAttribute("authUser", true);
        }
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
