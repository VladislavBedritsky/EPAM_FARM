package org.example.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * REST Controller which handle requests on "/rest"
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@RestController
@RequestMapping("/rest")
public class MainController {

    @GetMapping("/user")
    public Principal getUser(Principal principal) {
        return principal;
    }
}