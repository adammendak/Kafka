package com.adammendak.core.service.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class PrinterLoggingAspect {

    @Before("execution(* com.adammendak.core.service.PrinterService.getMessage(..))")
    public void logBefore(final JoinPoint joinPoint) {
        log.info("#### ASPECT EXECUTION");
        log.info("Class " + joinPoint.getTarget());
        log.info("Method " + joinPoint.getSignature() + " begins");
        log.info("#### ASPECT END");
    }
}
