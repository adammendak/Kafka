package com.adammendak.microservice.currencyconversionmicroservice.controller;

import com.adammendak.microservice.currencyconversionmicroservice.controller.proxy.CurrencyExchangeProxy;
import com.adammendak.microservice.currencyconversionmicroservice.model.CurrencyConversion;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/currency-converter")
public class CurrencyConversionController {

    private CurrencyExchangeProxy currencyExchangeProxy;
    private ObjectMapper objectMapper;

    public CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy, ObjectMapper objectMapper) {
        this.currencyExchangeProxy = currencyExchangeProxy;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public String test() {
        return "test";
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion converCurrency(@PathVariable(name = "from") String from,
                                             @PathVariable(name = "to") String to,
                                             @PathVariable(name = "quantity") BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://127.0.0.1:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVariables);

        CurrencyConversion responseEntity = response.getBody();
        log.info("response : " + responseEntity.toString());

        return new CurrencyConversion(responseEntity.getId(), from, to, responseEntity.getConversionMultiple(), quantity,
                quantity.multiply(responseEntity.getConversionMultiple()), responseEntity.getPort());
    }

    //feign example
    @GetMapping("feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion converCurrencyFeign(@PathVariable(name = "from") String from,
                                             @PathVariable(name = "to") String to,
                                             @PathVariable(name = "quantity") BigDecimal quantity) {

        ResponseEntity<CurrencyConversion> responseEntity = currencyExchangeProxy.retrieveExchangeValue(from,to);
        CurrencyConversion response = responseEntity.getBody();

        log.info("response : " + responseEntity.toString());
        return new CurrencyConversion(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
