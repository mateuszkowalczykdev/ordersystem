package dev.mateuszkowalczyk.ordersystem.order.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderNumberGenerator {
    
    public static String generate() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
    
}
