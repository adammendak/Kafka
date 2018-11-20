package com.adammendak.core.service.printer.events;

import org.springframework.context.ApplicationEvent;

public class PrinterMessageEvent extends ApplicationEvent {

    private String message;

    public PrinterMessageEvent(Object source) {
        super(source);
    }

    public PrinterMessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    @Override
    public String toString() {
        return "####" + getClass().getName() + "[source=" + source + "] \n" +
                " #### MESSAGE = " + message;
    }
}
