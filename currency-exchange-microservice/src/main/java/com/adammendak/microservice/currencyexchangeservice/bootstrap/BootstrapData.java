package com.adammendak.microservice.currencyexchangeservice.bootstrap;

import com.adammendak.microservice.currencyexchangeservice.model.ExchangeValue;
import com.adammendak.microservice.currencyexchangeservice.service.ExchangeValueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class BootstrapData implements CommandLineRunner {

    private Environment env;
    private ExchangeValueService exchangeValueService;

    public BootstrapData(Environment env, ExchangeValueService exchangeValueService) {
        this.env = env;
        this.exchangeValueService = exchangeValueService;
    }

    @Override
    public void run(String... args) throws Exception {
        ExchangeValue exchangeValue1 = new ExchangeValue(1L, "from1","to1", BigDecimal.ONE,Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        exchangeValue1.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        exchangeValueService.saveExchangeValue(exchangeValue1);

        ExchangeValue exchangeValue2 = new ExchangeValue(2L,"from2","to3", BigDecimal.ONE, Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        exchangeValue1.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        exchangeValueService.saveExchangeValue(exchangeValue2);

        ExchangeValue exchangeValue3 = new ExchangeValue(3L, "from2","to3", BigDecimal.ONE, Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        exchangeValue1.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        exchangeValueService.saveExchangeValue(exchangeValue3);
    }
}
