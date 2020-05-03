package org.example.epam.rest.controller;

import org.example.epam.backend.model.Session;
import org.example.epam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * class RestUserController
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    /**
     * Get principal user
     *
     * @param user Principal user
     * @return user
     */
    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/saveSession")
    public Session saveSession(@RequestBody Session session) {
        userService.saveSession(session);
        return session;
    }

}
