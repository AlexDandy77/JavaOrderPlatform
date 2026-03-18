package dev.alexeydandy.paymentservice.domain;

import dev.alexeydandy.api.http.payment.CreatePaymentRequestDto;
import dev.alexeydandy.api.http.payment.PaymentMethod;
import dev.alexeydandy.paymentservice.domain.db.PaymentEntityMapper;
import dev.alexeydandy.paymentservice.domain.db.PaymentEntityRepository;
import dev.alexeydandy.api.http.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentService {

    private final PaymentEntityMapper mapper;
    private final PaymentEntityRepository repository;

    public dev.alexeydandy.api.http.payment.CreatePaymentResponseDto makePayment(CreatePaymentRequestDto request) {
        var found = repository.findByOrderId(request.orderId());
        if (found.isPresent()) {
            log.info("Payment already exists for orderId {}", request.orderId());
            return mapper.toResponseDto(found.get());
        }

        var entity = mapper.toEntity(request);

        var status = request.paymentMethod().equals(PaymentMethod.QR)
                ? PaymentStatus.PAYMENT_FAILED
                : PaymentStatus.PAYMENT_SUCCEEDED;

        entity.setPaymentStatus(status);
        var savedEntity = repository.save(entity);
        return mapper.toResponseDto(savedEntity);
    }
}
