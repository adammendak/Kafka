package com.adammendak.microservice.currencyconversionmicroservice.controller;

import com.adammendak.microservice.currencyconversionmicroservice.model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConversionController {

    @Autowired
    Environment env;

    @GetMapping
    public String test() {
        return "test";
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion converCurrency(@PathVariable(name = "from") String from,
                                             @PathVariable(name = "to") String to,
                                             @PathVariable(name = "quantity") BigDecimal quantity) {
        return new CurrencyConversion(1L, from, to, BigDecimal.ONE, quantity, BigDecimal.ZERO,
                Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
    }
}
