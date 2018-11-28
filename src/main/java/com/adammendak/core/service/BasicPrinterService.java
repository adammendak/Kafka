package com.adammendak.core.service;

import com.adammendak.core.events.PrinterMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Primary
@RequestScope
@Slf4j
public class BasicPrinterService implements PrinterService{

    private String MESSAGE = "Hello From Basic Service";
    private String EVENT_MESSAGE = "Event Strzelony z Basic Printer Service";

    private ApplicationEventPublisher publisher;

    public BasicPrinterService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostConstruct
    public void init() {
        log.info("creating request scoped basic printer service");
    }

    @PreDestroy
    public void destroy() {
        log.info("destroying basic printer bean");
    }

    @Override
    public String getMessage() {
        publisher.publishEvent(new PrinterMessageEvent(this, EVENT_MESSAGE));
        return MESSAGE;
    }
}
