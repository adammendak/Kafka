package com.adammendak.core.controller;

import com.adammendak.core.service.printer.PrinterService;
import com.adammendak.core.service.printer.aspects.AnnotationLogger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HomeController {

    private PrinterService printerService;

//    public HomeController(@Qualifier(value = "elaborated") PrinterService printerService) {
//        this.printerService = printerService;
//    }

    public HomeController(PrinterService printerService) {
        this.printerService = printerService;
    }

    @AnnotationLogger
    @RequestMapping(name = "/hello")
    public String helloWorld() {
        printerService.testMethod();
        return printerService.getMessage();
    }
}
