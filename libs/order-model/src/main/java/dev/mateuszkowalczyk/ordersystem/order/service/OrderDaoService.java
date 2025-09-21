package dev.mateuszkowalczyk.ordersystem.order.service;

import dev.mateuszkowalczyk.ordersystem.order.model.Order;
import dev.mateuszkowalczyk.ordersystem.order.model.OrderQuery;
import dev.mateuszkowalczyk.ordersystem.order.util.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderDaoService {
    public static final String CREATED_AT = "createdAt";
    public static final String ORDER_ID = "orderId";
    public static final String STATUS = "status";
    public static final String USER_ID = "userId";
    private final MongoTemplate mongoTemplate;

    public List<Order> filter(final OrderQuery orderQuery) {
        var query = new Query();

        if (Objects.nonNull(orderQuery.createdFrom()) && Objects.isNull(orderQuery.createdTo())) {
            query.addCriteria(Criteria.where(CREATED_AT).gte(orderQuery.createdFrom()));
        }
        if (Objects.nonNull(orderQuery.createdTo()) && Objects.isNull(orderQuery.createdFrom())) {
            query.addCriteria(Criteria.where(CREATED_AT).lte(orderQuery.createdTo()));
        }
        if (Objects.nonNull(orderQuery.createdTo()) && Objects.nonNull(orderQuery.createdFrom())) {
            query.addCriteria(Criteria.where(CREATED_AT).lt(orderQuery.createdTo()).gt(orderQuery.createdFrom()));
        }
        if (Objects.nonNull(orderQuery.orderId())) {
            query.addCriteria(Criteria.where(ORDER_ID).is(orderQuery.orderId()));
        }
        if (Objects.nonNull(orderQuery.statuses())) {
            query.addCriteria(Criteria.where(STATUS).in(orderQuery.statuses()));
        }
        if (Objects.nonNull(orderQuery.userId())) {
            query.addCriteria(Criteria.where(USER_ID).is(orderQuery.userId()));
        }

        return mongoTemplate.find(query, Order.class);
    }

    public Optional<Order> updateStatus(final String orderId, final OrderStatus status) {
        final var query = new Query();
        query.addCriteria(Criteria.where(ORDER_ID).is(orderId));

        final var update = new Update();
        update.set(STATUS, status);

        return Optional.ofNullable(
                mongoTemplate.findAndModify(query, update, Order.class)
        );
    }
}
