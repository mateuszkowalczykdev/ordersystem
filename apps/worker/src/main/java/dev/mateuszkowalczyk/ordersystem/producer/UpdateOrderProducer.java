package dev.mateuszkowalczyk.ordersystem.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateOrderProducer {

    public static final String TOPIC = "update-orders";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void updateOrder(String orderId) {
        kafkaTemplate.send(TOPIC, orderId);
        log.info("Update order [orderId={}]", orderId);
    }

}
