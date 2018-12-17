package com.adammendak.elastic.service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TweetConsumer {

    @KafkaListener(topics = "twitter_tweets")
    public void insertTweetsIntoElastic(String message) {
        log.info(message);
    }
}
