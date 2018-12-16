package com.adammendak.core.service;

import com.adammendak.core.model.Tweet;
import com.adammendak.core.repository.TweetRepository;
import org.springframework.stereotype.Service;

@Service
public class TweetService {

    private TweetRepository tweetRepository;

    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public void saveTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

}
