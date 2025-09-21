package dev.mateuszkowalczyk.ordersystem.service;

import dev.mateuszkowalczyk.ordersystem.order.model.Order;
import dev.mateuszkowalczyk.ordersystem.order.service.OrderDaoService;
import dev.mateuszkowalczyk.ordersystem.order.util.OrderStatus;
import dev.mateuszkowalczyk.ordersystem.producer.UpdateOrderProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateOrderStatusService {
    private final ThreadPoolTaskScheduler scheduler;
    private final UpdateOrderProducer updateOrderProducer;
    private final OrderDaoService orderDaoService;

    public void update(String orderId) {
        long delaySec = ThreadLocalRandom.current().nextLong(10, 61);
        Instant runAt = Instant.now().plusSeconds(delaySec);

        scheduler.schedule(() -> {
            Optional<Order> order = orderDaoService.updateStatus(orderId, getRandomStatus());
            log.info("Order status: {}", order.get());

            updateOrderProducer.updateOrder(orderId);

            log.debug("Process order status [orderId={}, delay={}]", orderId, delaySec);
        }, Date.from(runAt));
    }

    private OrderStatus getRandomStatus() {
        final var statuses = List.of(OrderStatus.CANCELED, OrderStatus.COMPLETED);

        return statuses.get(
                ThreadLocalRandom.current().nextInt(statuses.size())
        );
    }
}
