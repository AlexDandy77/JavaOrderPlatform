package dev.alexeydandy.api.http.order;

import java.util.Set;
import lombok.Builder;

@Builder
public record CreateOrderRequestDto (
    Long customerId,
    String address,
    Set<OrderItemRequestDto> items
) {}
