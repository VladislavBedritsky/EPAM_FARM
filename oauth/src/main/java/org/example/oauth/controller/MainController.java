package org.example.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/rest")
public class MainController {

    @GetMapping("/user")
    public Principal getUser(Principal principal) {
        return principal;
    }
}