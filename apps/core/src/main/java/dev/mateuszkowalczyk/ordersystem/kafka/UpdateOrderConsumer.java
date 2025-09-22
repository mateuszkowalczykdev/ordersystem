package dev.mateuszkowalczyk.ordersystem.kafka;

import dev.mateuszkowalczyk.ordersystem.order.service.OrderChangesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateOrderConsumer {
    public static final String GROUP_ID = "order-system-core";
    public static final String UPDATE_ORDERS_TOPIC = "update-orders";
    public static final String NEW_ORDERS_TOPIC = "new-orders";
    private final OrderChangesService orderChangesService;


    @KafkaListener(topics = UPDATE_ORDERS_TOPIC, groupId = GROUP_ID)
    public void updateOrderTopic(String orderId) {
        orderChangesService.noticeChangedOrder(orderId);
        log.info("Update order event [orderId={}]", orderId);
    }

    @KafkaListener(topics = NEW_ORDERS_TOPIC, groupId = GROUP_ID)
    public void newOrderTopic(String orderId) {
        orderChangesService.noticeChangedOrder(orderId);
        log.info("New order event [orderId={}]", orderId);
    }


}
