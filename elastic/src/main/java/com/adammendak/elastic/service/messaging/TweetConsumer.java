package com.adammendak.elastic.service.messaging;

import com.adammendak.elastic.service.ElasticSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TweetConsumer {

    private ElasticSearchService elasticSearchService;

    public TweetConsumer(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @KafkaListener(topics = "twitter_tweets")
    public void insertTweetsIntoElastic(String message) {
        elasticSearchService.insertTweet(message);
    }
}
