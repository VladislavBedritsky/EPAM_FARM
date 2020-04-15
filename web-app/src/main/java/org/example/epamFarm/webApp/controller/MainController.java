package org.example.epamFarm.webApp.controller;

import org.example.epamFarm.backend.service.UserService;
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
        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(model);
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
