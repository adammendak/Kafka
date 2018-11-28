package com.adammendak.core.service.printer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Qualifier("elaborated")
@Slf4j
public class ElaboratedPrinterService implements PrinterService {

    private String MESSAGE = "Elaborated Message";

    @PostConstruct
    public void init() {
        log.info("creating Elaborated printer service");
    }

    @PreDestroy
    public void destroy() {
        log.info("destroying Elaborated printer bean");
    }

    @Override
    public String testMethod() {
        return "testMethod";
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
