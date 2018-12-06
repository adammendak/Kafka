package com.adammendak.core.bootstrap;

import com.adammendak.core.model.Product;
import com.adammendak.core.repository.ProductRepository;
import com.adammendak.core.service.printer.aspects.AnnotationLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class Bootstrap implements CommandLineRunner {

    private final ProductRepository productRepository;

    public Bootstrap(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @AnnotationLogger
    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product("testProduct1"));
        productRepository.save(new Product("testProduct2"));
    }
}
