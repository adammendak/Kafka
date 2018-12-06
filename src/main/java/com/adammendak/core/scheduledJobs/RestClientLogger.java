package com.adammendak.core.scheduledJobs;

import com.adammendak.core.model.Product;
import com.adammendak.core.service.ProductService;
import com.adammendak.core.service.printer.aspects.AnnotationLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Component
@Slf4j
public class RestClientLogger {

    String PRODUCT_URI = "http://localhost:8080/product/";

    final ProductService productService;

    public RestClientLogger(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(fixedRate = 10000)
    @AnnotationLogger
    public void logNumberOfProductsInDB() {

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> result = restTemplate.getForObject(PRODUCT_URI, List.class);
        log.info("#### SCHEDULED_JOB ");
        log.info("### number Of Products in DB " + result.size());
        result.forEach( (r) -> {
            log.info("### product " + r.toString());
        });
        log.info("#### END JOB");
    }
}
