package dev.alexeydandy.deliveryservice.kafka;

import dev.alexeydandy.api.kafka.OrderPaidEvent;
import dev.alexeydandy.deliveryservice.domain.DeliveryProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@EnableKafka
@Configuration
@AllArgsConstructor
public class OrderPaidKafkaConsumer {

    private final DeliveryProcessor deliveryProcessor;

    @KafkaListener(
            topics= "${order-paid-topic}",
            containerFactory = "orderPaidEventListenerFactory"
    )
    public void listen(OrderPaidEvent event) {
        log.info("Received order paid event: {}", event);
        deliveryProcessor.processOrderPaid(event);
    }
}
