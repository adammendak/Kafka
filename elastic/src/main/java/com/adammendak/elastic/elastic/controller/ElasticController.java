package com.adammendak.elastic.elastic.controller;

import com.adammendak.elastic.elastic.service.ElasticSearchConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elastic")
@Slf4j
public class ElasticController {

    private ElasticSearchConsumer elasticSearchConsumer;

    public ElasticController(ElasticSearchConsumer elasticSearchConsumer) {
        this.elasticSearchConsumer = elasticSearchConsumer;
    }

    @GetMapping
    public ResponseEntity<?> testElasticInsert() {
        elasticSearchConsumer.insertTestData();
        return ResponseEntity.ok("success - check log");
    }
}
