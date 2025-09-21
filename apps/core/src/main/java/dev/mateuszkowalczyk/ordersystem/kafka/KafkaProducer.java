package dev.mateuszkowalczyk.ordersystem.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    public static final String TOPIC = "new-orders";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void createdNewOrder(String orderId) {
        kafkaTemplate.send(TOPIC, orderId);
        log.info("Created new order with orderId={}", orderId);
    }

}
