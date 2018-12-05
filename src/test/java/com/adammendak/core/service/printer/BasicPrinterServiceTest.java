package com.adammendak.core.service.printer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicPrinterServiceTest {

    @Autowired
    private BasicPrinterService basicPrinterService;

    @Test
    public void init() {
    }

    @Test
    public void destroy() {
    }

    @Test
    public void testMethod() {
        assertEquals("testMethod", basicPrinterService.testMethod());
    }

    @Test
    public void getMessage() {
    }
}