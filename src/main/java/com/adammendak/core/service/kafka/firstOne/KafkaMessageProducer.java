package com.adammendak.core.service.kafka.firstOne;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class KafkaMessageProducer {

    final String BOOTSTRAP_SERVER = "127.0.0.1:9092";
    final String MESSAGE = "message straight from Spring long val: ";
    final Random random = new Random();
    final Properties properties = getProperties();

    public void createMessage() {
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", MESSAGE + random.nextLong());

        producer.send(record);
        producer.flush();
        producer.close();
    }

    public void createMessageWithCallback() {
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", String.valueOf(10),MESSAGE + random.nextLong() +
                " with Callback");

        for (int i = 0; i<10; i++) {
            //with same key goes to same partition in record key is entered
            producer.send(record, (recordMetadata, e) -> {
                if (e == null) {
                    log.info("metadata: TOPIC: " + recordMetadata.topic());
                    log.info("metadata: PARTITION: " + recordMetadata.partition());
                    log.info("metadata: OFFSETS: " + recordMetadata.offset());
                    log.info("metadata: TIMESTAMP: " + recordMetadata.timestamp());
                } else {
                    log.error("error while producing" + e.getMessage());
                }
            });
        }
        producer.flush();
        producer.close();
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
