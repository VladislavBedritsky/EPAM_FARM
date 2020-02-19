package org.example.EPAM_FARM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"org.example"})
@ComponentScan(basePackages = {"org.example"})
public class Application  {
// extends SpringBootServletInitializer
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

//        @Override
//        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class);
//    }
}
