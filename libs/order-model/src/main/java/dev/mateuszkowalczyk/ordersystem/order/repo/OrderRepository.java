package dev.mateuszkowalczyk.ordersystem.order.repo;

import dev.mateuszkowalczyk.ordersystem.order.model.Order;
import dev.mateuszkowalczyk.ordersystem.order.util.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    Optional<Order> findByOrderId(String orderId);

    List<Order> findAllByStatusIn(List<OrderStatus> statuses);
}
