package org.example.EPAM_FARM.web_app.controller;

import org.example.EPAM_FARM.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${project.web-app.name}")
    private String ARTIFACT_NAME;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("artifact_name",ARTIFACT_NAME);
        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(model);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("artifact_name",ARTIFACT_NAME);
        return "login";
    }

    @GetMapping("/accessdenied")
    public String accessdenied(Model model) {
        model.addAttribute("artifact_name",ARTIFACT_NAME);
        return "denied";
    }
}
