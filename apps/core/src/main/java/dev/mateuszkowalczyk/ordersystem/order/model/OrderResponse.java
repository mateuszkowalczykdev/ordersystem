package dev.mateuszkowalczyk.ordersystem.order.model;

import dev.mateuszkowalczyk.ordersystem.order.util.OrderStatus;

import java.util.Date;

public record OrderResponse(String orderId, OrderStatus status, String userId, String description, Date createdAt) {
}
