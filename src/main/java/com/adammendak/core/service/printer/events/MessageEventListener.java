package com.adammendak.core.service.printer.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageEventListener implements ApplicationListener<PrinterMessageEvent> {

    @Override
    public void onApplicationEvent(PrinterMessageEvent printerMessageEvent) {
        log.info("##### PRZECHWYCONY EVENT");
        log.info(printerMessageEvent.toString());
    }
}
