package com.adammendak.core.controller;

import com.adammendak.core.service.kafka.firstOne.KafkaMessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerController {

    private KafkaMessageConsumer kafkaMessageConsumer;

    public ConsumerController(KafkaMessageConsumer kafkaMessageConsumer) {
        this.kafkaMessageConsumer = kafkaMessageConsumer;
    }

    @GetMapping("/firstOne")
    public String logAllMessagesFromThread() {
        kafkaMessageConsumer.consumeMessagesInThread();
        return "check logs";
    }
}
