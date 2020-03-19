package org.example.EPAM_FARM.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class RestUserController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
