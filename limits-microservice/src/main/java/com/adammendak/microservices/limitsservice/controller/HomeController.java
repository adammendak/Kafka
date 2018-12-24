package com.adammendak.microservices.limitsservice.controller;

import com.adammendak.microservices.limitsservice.configuration.ApplicationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class HomeController {

    private ApplicationProperties applicationProperties;

    public HomeController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @GetMapping
    public String homeController() {
        return "hello from limits microservice min" + applicationProperties.getMinimum() + " max " + applicationProperties.getMaximum();
    }
}
