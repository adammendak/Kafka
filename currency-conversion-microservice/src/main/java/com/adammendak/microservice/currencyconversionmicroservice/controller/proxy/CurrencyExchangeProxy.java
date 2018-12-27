package com.adammendak.microservice.currencyconversionmicroservice.controller.proxy;

import com.adammendak.microservice.currencyconversionmicroservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    ResponseEntity<CurrencyConversion> retrieveExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to);
}
