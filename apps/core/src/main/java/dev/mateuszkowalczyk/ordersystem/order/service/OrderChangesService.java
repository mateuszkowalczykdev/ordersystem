package dev.mateuszkowalczyk.ordersystem.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mateuszkowalczyk.ordersystem.order.mapper.OrderMapper;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderChangesService {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final OrderMapper orderMapper;
    private final OrderStorageService orderStorageService;
    private final ObjectMapper objectMapper;

    public SseEmitter getChangesStream() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        return emitter;
    }

    public void noticeChangedOrder(final String orderId) {
        orderStorageService.getByOrderId(orderId).ifPresent(order -> {
            try {
                noticeChangedOrder(orderMapper.map(order));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    public void noticeChangedOrder(final OrderResponse orderResponse) throws JsonProcessingException {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        String message = objectMapper.writeValueAsString(orderResponse);

        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().name("ORDER_CHANGE").data(message));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });

        emitters.removeAll(deadEmitters);
    }
}
