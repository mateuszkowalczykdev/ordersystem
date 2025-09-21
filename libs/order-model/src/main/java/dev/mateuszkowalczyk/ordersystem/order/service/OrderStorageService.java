package dev.mateuszkowalczyk.ordersystem.order.service;

import dev.mateuszkowalczyk.ordersystem.order.model.Order;
import dev.mateuszkowalczyk.ordersystem.order.repo.OrderRepository;
import dev.mateuszkowalczyk.ordersystem.order.util.OrderStatus;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderStorageService {
    private final OrderDaoService orderFilterService;
    private final OrderRepository orderRepository;

    public Order save(final Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getActive() {
        return orderRepository.findAllByStatusIn(
                List.of(OrderStatus.CREATED)
        );
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> getFiltered(final OrderQuery query) {
        return orderFilterService.filter(query);
    }
}
