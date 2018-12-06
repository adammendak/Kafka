package com.adammendak.core.bootstrap;

import com.adammendak.core.model.Product;
import com.adammendak.core.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("dev")
public class Bootstrap implements CommandLineRunner {

    private final ProductRepository productRepository;

    public Bootstrap(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product();
        product1.setName("testProduct1");
        productRepository.save(product1);
        log.info("### saved Product " + product1.toString());
    }
}
