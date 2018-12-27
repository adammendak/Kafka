package com.adammendak.microservice.currencyexchangeservice.service;

import com.adammendak.microservice.currencyexchangeservice.model.ExchangeValue;
import com.adammendak.microservice.currencyexchangeservice.repository.ExchangeValueRepository;
import org.springframework.stereotype.Service;

@Service
public class ExchangeValueService {

    private ExchangeValueRepository exchangeValueRepository;

    public ExchangeValueService(ExchangeValueRepository exchangeValueRepository) {
        this.exchangeValueRepository = exchangeValueRepository;
    }

    public void saveExchangeValue(ExchangeValue exchangeValue) {
        exchangeValueRepository.save(exchangeValue);
    }
}
