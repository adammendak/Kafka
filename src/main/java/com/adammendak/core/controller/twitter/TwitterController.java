package com.adammendak.core.controller.twitter;

import com.adammendak.core.service.kafka.twitter.TwitterProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

    private TwitterProducer twitterProducer;

    public TwitterController(TwitterProducer twitterProducer) {
        this.twitterProducer = twitterProducer;
    }

    @GetMapping
    public ResponseEntity<?> logTwitts() {
        twitterProducer.run();
        return ResponseEntity.ok().body("Check logs for Intercepted Twitts");
    }
}
