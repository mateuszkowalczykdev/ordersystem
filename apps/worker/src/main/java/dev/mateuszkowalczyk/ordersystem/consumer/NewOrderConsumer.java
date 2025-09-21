package dev.mateuszkowalczyk.ordersystem.consumer;

import dev.mateuszkowalczyk.ordersystem.service.UpdateOrderStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewOrderConsumer {
    public static final String GROUP_ID = "order-system";
    public static final String TOPIC = "new-orders";
    private final UpdateOrderStatusService updateOrderStatusService;


    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void flightEventConsumer(String orderId) {
        log.info("Consumer consume Kafka message -> {}", orderId);

        updateOrderStatusService.update(orderId);
    }


}
