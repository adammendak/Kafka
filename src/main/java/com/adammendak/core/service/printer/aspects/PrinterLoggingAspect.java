package com.adammendak.core.service.printer.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class PrinterLoggingAspect {

//    @Before("execution(* com.adammendak.core.service.printer.PrinterService.getMessage(..))") mozna tylko tyle zostawic i dziala
    @Before("MessagePrinterPointcuts.loggingOperation()")
    public void logBefore(final JoinPoint joinPoint) {
        log.info("#### ASPECT EXECUTION");
        log.info("Class " + joinPoint.getTarget());
        log.info("Method " + joinPoint.getSignature() + " begins");
        log.info("#### ASPECT END");
    }

    @Before("MessagePrinterPointcuts.logAnyMethodInController()")
    public void logController(final JoinPoint joinPoint) {
        log.info("#### CONTROLLER ASPECT EXECUTION");
        log.info("Class " + joinPoint.getTarget());
        log.info("Method " + joinPoint.getSignature() + " begins");
        log.info("#### ASPECT END");
    }

    @Before("MessagePrinterPointcuts.logAnnotationPointcuts()")
    public void logAnnotationLogger(final JoinPoint joinPoint) {
        log.info("#### ANNOTATION METHOD");
        log.info("Class " + joinPoint.getTarget());
        log.info("Method " + joinPoint.getSignature() + " begins");
        log.info("#### ASPECT END");
    }
}
