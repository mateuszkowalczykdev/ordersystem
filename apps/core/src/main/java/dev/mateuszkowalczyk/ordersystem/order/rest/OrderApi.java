package dev.mateuszkowalczyk.ordersystem.order.rest;

import dev.mateuszkowalczyk.ordersystem.order.model.OrderRequest;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderResponse;
import dev.mateuszkowalczyk.ordersystem.order.service.OrderService;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController()
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderApi {
    private final OrderService orderService;

    @PostMapping
    public void order(@Valid @RequestBody OrderRequest order) {
        orderService.createOrder(order);
    }

    @GetMapping("/active")
    public List<OrderResponse> getActiveOrders() {
        return orderService.getActiveOrders();
    }

    @GetMapping("/all")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAll();
    }

    @PostMapping("/filter")
    public List<OrderResponse> filterOrders(@RequestBody OrderQuery orderQuery) {
        return orderService.getFiltered(orderQuery);
    }

    @GetMapping("/changes")
    public SseEmitter streamChanges() {
        return orderService.getStreamChanges();
    }
}
