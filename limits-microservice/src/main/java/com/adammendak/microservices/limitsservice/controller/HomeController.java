package com.adammendak.microservices.limitsservice.controller;

import com.adammendak.microservices.limitsservice.configuration.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class HomeController {

    private Configuration configuration;

    public HomeController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping
    public String homeController() {
        return "hello from limits microservice min" + configuration.getMinimum() + " max " +configuration.getMaximum();
    }
}
