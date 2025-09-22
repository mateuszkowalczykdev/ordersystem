package dev.mateuszkowalczyk.ordersystem.consumer;

import dev.mateuszkowalczyk.ordersystem.service.UpdateOrderStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewOrderConsumer {
    public static final String GROUP_ID = "order-system-worker";
    public static final String TOPIC = "new-orders";
    private final UpdateOrderStatusService updateOrderStatusService;


    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void newOrderConsume(String orderId) {
        log.info("New order event [orderId={}]", orderId);

        updateOrderStatusService.update(orderId);
    }


}
