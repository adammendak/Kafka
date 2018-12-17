package com.adammendak.elastic.controller;

import com.adammendak.elastic.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elastic")
@Slf4j
public class ElasticController {

    private ElasticSearchService elasticSearchService;

    public ElasticController(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @GetMapping
    public ResponseEntity<?> testElasticInsert() {
        elasticSearchService.insertTestData();
        return ResponseEntity.ok("success - check log");
    }
}
