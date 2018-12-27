package com.adammendak.microservice.currencyconversionmicroservice.controller.proxy;

import com.adammendak.microservice.currencyconversionmicroservice.model.CurrencyConversion;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//to jest zahardkodowane, kiedy nie mamy serwera nazw
//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")
@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    ResponseEntity<CurrencyConversion> retrieveExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to);
}
