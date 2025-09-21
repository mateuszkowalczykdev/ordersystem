package dev.mateuszkowalczyk.ordersystem.order.model;

import jakarta.validation.constraints.NotBlank;

public record OrderRequest (@NotBlank(message = "UserId Cannot be empty") String userId, @NotBlank(message = "Description cannot be empty") String description) {
}
