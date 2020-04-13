package org.example.EPAM_FARM.web_app.controller;

import org.example.EPAM_FARM.backend.model.User;
import org.example.EPAM_FARM.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegistrationFrom() {
        return "registration";
    }

    @PostMapping
    public String saveUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String email,
            @RequestParam String birthday,
            Model model

    ) {

        User registrationUser = new User(firstName,lastName,username,password,email,birthday);

        if (!password.equals(confirmPassword)) {
            model.addAttribute("diffPassw","Different passwords.");
            return "registration";
        }

        if(!userService.saveUser(registrationUser)) {
            model.addAttribute("isExists","Such login is already exists!");
            return "registration";
        }

        return "redirect:/login";
    }

}
