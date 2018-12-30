package com.adammendak.microservices.limitsservice.controller;

import com.adammendak.microservices.limitsservice.configuration.ApplicationProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
@Slf4j
public class HomeController {

    private ApplicationProperties applicationProperties;

    public HomeController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @GetMapping
    public String homeController() {
        return "hello from limits microservice min" + applicationProperties.getMinimum() + " max " + applicationProperties.getMaximum();
    }

    @GetMapping("/fault")
    @HystrixCommand(fallbackMethod = "fallbackTestMethod" )
    public String FaultToleranthomeController() {
        throw new RuntimeException("exception");
    }

    public String fallbackTestMethod() {
        log.info("######Wywalilo sie ");
        return "Fault tolerant gracefull";
    }
}
