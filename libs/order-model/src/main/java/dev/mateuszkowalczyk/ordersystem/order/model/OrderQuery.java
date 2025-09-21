package dev.mateuszkowalczyk.ordersystem.order.model;

import dev.mateuszkowalczyk.ordersystem.order.util.OrderStatus;

import java.util.Date;
import java.util.List;

public record OrderQuery(Date createdFrom, Date createdTo, String orderId, List<OrderStatus> statuses, String userId) {
}
