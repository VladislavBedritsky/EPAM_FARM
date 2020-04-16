package org.example.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * SpringBootServletInitializer child class
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@SpringBootApplication
public class OauthServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class, args);
    }
}
