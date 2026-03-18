package dev.alexeydandy.paymentservice.domain.db;

import dev.alexeydandy.api.http.payment.CreatePaymentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentEntityMapper {
    PaymentEntity toEntity(CreatePaymentRequestDto request);

    @Mapping(source = "id", target = "paymentId")
    dev.alexeydandy.api.http.payment.CreatePaymentResponseDto toResponseDto(PaymentEntity entity);
}
