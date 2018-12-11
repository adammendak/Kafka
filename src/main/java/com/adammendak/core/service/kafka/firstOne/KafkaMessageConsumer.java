package com.adammendak.core.service.kafka.firstOne;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class KafkaMessageConsumer {

    final String BOOTSTRAP_SERVER = "127.0.0.1:9092";
    final String GROUP_ID = "My_Java_Application";
    final String topic = "first_topic";
    final CountDownLatch latch = new CountDownLatch(1);

    public ConsumerRecords<String, String> consumeMessages() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getProperties());
        consumer.subscribe(Collections.singleton(topic));

        while (true) {
            ConsumerRecords<String, String> result = consumer.poll(Duration.ofMillis(100));
            result.forEach(rec -> {
                log.info("Key: " + rec.key() + " Value: " + rec.value());
                log.info("Partition: " + rec.partition() + " Offset: " + rec.offset());
                log.info("------------");
            });
        }
    }

    public void consumeMessagesInThread() {
        Runnable consumerThread = new ConsumerThread(latch);
        Thread thread = new Thread(consumerThread);
        thread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
          log.info("shutdown hook");
            ((ConsumerThread) consumerThread).shutDown();
        }));

        try {
            latch.await();
        } catch (InterruptedException e) {
           log.error("application got interrupted");
        } finally {
            log.info("+++++ application is closing #####");
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");  //earlisest,latest,none
        return properties;
    }

    public class ConsumerThread implements Runnable {

        private CountDownLatch latch;
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getProperties());

        public ConsumerThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    consumer.subscribe(Collections.singleton(topic));
                    ConsumerRecords<String, String> result = consumer.poll(Duration.ofMillis(100));
                    result.forEach(rec -> {
                        log.info("Key: " + rec.key() + " Value: " + rec.value());
                        log.info("Partition: " + rec.partition() + " Offset: " + rec.offset());
                        log.info("------------");
                    });
                }
            } catch (WakeupException e) {
                log.error("Received shutdown signal");
                log.error("--------------------");
            } finally {
                consumer.close();
                latch.countDown();
            }
        }

        public void shutDown() {
            consumer.wakeup();
        }
    }
}
