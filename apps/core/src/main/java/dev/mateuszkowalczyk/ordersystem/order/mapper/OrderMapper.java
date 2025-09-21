package dev.mateuszkowalczyk.ordersystem.order.mapper;

import dev.mateuszkowalczyk.ordersystem.order.model.Order;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderRequest;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", constant = "CREATED")
    @Mapping(target = "orderId", expression = "java(dev.mateuszkowalczyk.ordersystem.order.util.OrderNumberGenerator.generate())")
    Order create(OrderRequest orderRequest);

    OrderResponse map(Order order);

    List<OrderResponse> map(List<Order> orders);
}
