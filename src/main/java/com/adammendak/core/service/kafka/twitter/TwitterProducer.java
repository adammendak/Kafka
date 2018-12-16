package com.adammendak.core.service.kafka.twitter;

import com.adammendak.core.service.kafka.springKafka.KafkaMessageProducerService;
import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@Slf4j
public class TwitterProducer {

    private KafkaMessageProducerService kafkaMessageProducerService;

    public TwitterProducer(KafkaMessageProducerService kafkaMessageProducerService) {
        this.kafkaMessageProducerService = kafkaMessageProducerService;
    }

    public void run() {

        //** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(1000);

        Client client = createTwitterClient(msgQueue);
        // Attempts to establish a connection.
        client.connect();

        // on a different thread, or multiple different threads....
        while (!client.isDone()) {
            String msg = null;
            try {
//                msg = msgQueue.poll(1, TimeUnit.SECONDS);
                msg = msgQueue.take();
            } catch (InterruptedException e) {
                log.error("Error with Twitter: " + e.getMessage());
                client.stop();
            }
            if(msg != null) {
//                log.warn("MESSAGE TWITT : " + msg);
                kafkaMessageProducerService.sendMessageWithTopic("twitter_tweets", msg);
            }
        }
        client.stop();
        log.warn("END OF TWEET FEED");
    }


    private Client createTwitterClient(BlockingQueue<String> msgQueue) {

        /** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        // Optional: set up some followings and track terms
//        List<Long> followings = Lists.newArrayList(1234L, 566788L);
//        List<String> terms = Lists.newArrayList("twitter", "api");
        List<String> terms = Lists.newArrayList("bitcoin");
//        hosebirdEndpoint.followings(followings);
        hosebirdEndpoint.trackTerms(terms);

        // These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1(TwitterConstants.CONSUMER_KEY.getValue(), TwitterConstants.CONSUMER_SECRET.getValue(),
                TwitterConstants.TOKEN.getValue(), TwitterConstants.TOKEN_SECRET.getValue());


        ClientBuilder builder = new ClientBuilder()
                .name("TwitterClient-01")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue));

        return builder.build();
    }
}
