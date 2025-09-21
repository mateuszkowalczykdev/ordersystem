package dev.mateuszkowalczyk.ordersystem.order.model;

import dev.mateuszkowalczyk.ordersystem.order.util.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Document("orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;
    @CreatedDate
    private Date createdAt;
    @Indexed(unique = true)
    private String orderId;
    @Setter
    private OrderStatus status;
    private String userId;
    private String description;
}
