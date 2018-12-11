package com.adammendak.core.controller;

import com.adammendak.core.service.kafka.firstOne.KafkaMessageProducer;
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
    private KafkaMessageProducer kafkaMessageProducer;

//    public HomeController(@Qualifier(value = "elaborated") PrinterService printerService) {
//        this.printerService = printerService;
//    }


    public HomeController(PrinterService printerService, KafkaMessageProducer kafkaMessageProducer) {
        this.printerService = printerService;
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @AnnotationLogger
    @RequestMapping(name = "/hello")
    public String helloWorld() {
        printerService.testMethod();
//        kafkaMessageProducer.createMessage();
        kafkaMessageProducer.createMessageWithCallback();
        return printerService.getMessage();
    }
}
