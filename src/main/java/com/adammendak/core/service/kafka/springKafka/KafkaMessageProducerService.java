package com.adammendak.core.service.kafka.springKafka;

import com.adammendak.core.config.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageProducerService {

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        log.info("---- Producing message :" + msg + " \n For Topic : " + KafkaConstants.SPRING_TOPIC_NAME);
        kafkaTemplate.send(KafkaConstants.SPRING_TOPIC_NAME, msg);
    }

}
