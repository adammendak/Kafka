package com.adammendak.microservice.currencyexchangeservice.controller;

import com.adammendak.microservice.currencyexchangeservice.model.ExchangeValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    Environment env;


    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<ExchangeValue> retrieveExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to) {
        ExchangeValue exchangeValue = new ExchangeValue(1L, "from","to", BigDecimal.ONE, Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        exchangeValue.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        log.info("FROM exchange-service: {}", exchangeValue);

        return ResponseEntity.ok(exchangeValue);
    }
}
