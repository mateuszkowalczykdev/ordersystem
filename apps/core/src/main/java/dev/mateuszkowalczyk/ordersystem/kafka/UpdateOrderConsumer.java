package dev.mateuszkowalczyk.ordersystem.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateOrderConsumer {
    public static final String GROUP_ID = "order-system";
    public static final String TOPIC = "update-orders";


    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void flightEventConsumer(String orderId) {
        log.info("UpdateOrderConsumer consume Kafka message -> {}", orderId);
    }


}
