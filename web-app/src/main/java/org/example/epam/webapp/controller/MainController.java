package org.example.epam.webapp.controller;

import org.example.epam.backend.model.Session;
import org.example.epam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller that handle requests on "/", "/login",
 * "/accessdenied"
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;

    /**
     * Get view "index"
     *
     * @param model Model
     * @return index.html
     */
    @GetMapping
    public String getIndex(Model model) {
        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseThanAddToModel(model);
        Session session = new Session(
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                "web-app"
        );
        userService.saveSession(session);
        return "index";
    }

    /**
     * Get view "login"
     *
     * @return login.html
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Get view "denied"
     *
     * @return denied.html
     */
    @GetMapping("/accessdenied")
    public String accessdenied() {
        return "denied";
    }
}
