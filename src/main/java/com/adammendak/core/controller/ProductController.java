package com.adammendak.core.controller;

import com.adammendak.core.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String productName) {
        return new ResponseEntity<>(productService.findByName(productName), HttpStatus.OK);
    }
}
