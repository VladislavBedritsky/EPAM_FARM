package org.example.epam.webapp.controller;

import org.example.epam.backend.model.User;
import org.example.epam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Controller that handle requests about registration
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Controller
@RequestMapping("/registration")
@Validated
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegistrationFrom() {
        return "registration";
    }

    @PostMapping
    public String saveUser(
            @RequestParam
            @NotBlank(message = "First name can't be empty")
                    String firstName,
            @RequestParam
            @NotBlank(message = "Last name can't be empty")
                    String lastName,
            @RequestParam
            @NotBlank(message = "Username can't be empty")
                    String username,
            @RequestParam
            @NotBlank(message = "Password can't be empty")
                    String password,
            @RequestParam
            @NotBlank(message = "Confirm password can't be empty")
                    String confirmPassword,
            @RequestParam
            @NotBlank(message = "Email can't be empty")
            @Email
                    String email,
            @RequestParam
            @NotBlank(message = "Birthday can't be empty")
                    String birthday,
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
