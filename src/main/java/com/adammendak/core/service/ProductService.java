package com.adammendak.core.service;

import com.adammendak.core.model.Product;
import com.adammendak.core.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        productRepository.findAll().forEach(result::add);
        return result;
    }
}
