package com.adammendak.core.controller.kafka;

import com.adammendak.core.service.kafka.springKafka.KafkaMessageProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka/producer")
@Slf4j
public class KafkaProducerController {

    private KafkaMessageProducerService kafkaMessageProducerService;

    public KafkaProducerController(KafkaMessageProducerService kafkaMessageProducerService) {
        this.kafkaMessageProducerService = kafkaMessageProducerService;
    }

    //easier to test in web
    @GetMapping("/{message}")
    public ResponseEntity<?> produceMessageFromPath(@PathVariable("message") String message) {
        kafkaMessageProducerService.sendMessage(message);
        return ResponseEntity.ok().body("Message created: " + message);
    }
}
