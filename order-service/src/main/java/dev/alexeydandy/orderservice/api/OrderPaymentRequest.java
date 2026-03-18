package dev.alexeydandy.orderservice.api;

import dev.alexeydandy.api.http.payment.PaymentMethod;

public record OrderPaymentRequest(
        PaymentMethod paymentMethod
) {}