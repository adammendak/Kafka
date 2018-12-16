package com.adammendak.core.service.kafka.twitter;

import com.adammendak.core.model.Tweet;
import com.adammendak.core.service.TweetService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class TwitterConsumer {

    private TweetService tweetService;
    ObjectMapper objectMapper = new ObjectMapper();

    public TwitterConsumer(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @KafkaListener(topics = "twitter_tweets", groupId = "tweets")
    public void listen(String message) {

        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            tweetService.saveTweet(new Tweet(jsonNode.get("created_at").toString(), jsonNode.get("text").toString()));
        } catch (IOException e) {
           log.error("Error in saving tweet: " + e.getMessage());
        }
    }
}
