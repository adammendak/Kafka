package com.adammendak.core.service.printer.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MessagePrinterPointcuts {

    // * na poczatku oznacza ze metody private/protected i wszystkie zwracane typy
//    @Pointcut("execution(* com.adammendak.core.service.printer.PrinterService.getMessage(..))") tutaj konkretna metoda bedzie getMessage
    @Pointcut("execution(* com.adammendak.core.service.printer.PrinterService.*(..))") //tutaj wszystkie metody z tej klasy
    public void loggingOperation() {
    }

    @Pointcut("execution(* com.adammendak.core.controller.HomeController.*(..))")
    public void logAnyMethodInController() {
    }

    @Pointcut("@annotation(com.adammendak.core.service.printer.aspects.AnnotationLogger)")
    public void logAnnotationPointcuts() {
    }
}
