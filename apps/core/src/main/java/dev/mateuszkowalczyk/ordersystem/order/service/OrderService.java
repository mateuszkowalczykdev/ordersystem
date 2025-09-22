package dev.mateuszkowalczyk.ordersystem.order.service;

import dev.mateuszkowalczyk.ordersystem.kafka.NewOrderProducer;
import dev.mateuszkowalczyk.ordersystem.order.mapper.OrderMapper;
import dev.mateuszkowalczyk.ordersystem.order.model.Order;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderRequest;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderResponse;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderStorageService orderStorageService;
    private final OrderMapper orderMapper;
    private final NewOrderProducer newOrderProducer;
    private final OrderChangesService orderChangesService;

    public void createOrder(OrderRequest order) {
        Order saved = orderStorageService.save(
                orderMapper.create(order)
        );

        newOrderProducer.createdNewOrder(saved.getOrderId());
    }

    public List<OrderResponse> getActiveOrders() {
        return orderMapper.map(
                orderStorageService.getActive()
        );
    }

    public List<OrderResponse> getAll() {
        return orderMapper.map(
                orderStorageService.getAll()
        );
    }

    public List<OrderResponse> getFiltered(OrderQuery query) {
        return orderMapper.map(
                orderStorageService.getFiltered(query)
        );
    }

    public SseEmitter getStreamChanges() {
        return orderChangesService.getChangesStream();
    }
}
