package org.example.epam.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
}
