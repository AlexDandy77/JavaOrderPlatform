package dev.alexeydandy.paymentservice.domain.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByOrderId(Long orderId);
}