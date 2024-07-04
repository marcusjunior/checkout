package com.br.checkout.infrastructure.messaging;

import com.br.checkout.domain.port.CheckoutRepository;
import com.br.checkout.infrastructure.messaging.dto.PaymentResultDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ResultQueueConsumer {

    private final CheckoutRepository repository;

    public ResultQueueConsumer(CheckoutRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "result-queue")
    public void handleOrderStatusUpdated(PaymentResultDTO paymentResultDTO) {
        repository.updateOrderStatus(paymentResultDTO.getData().getId(), String.valueOf(paymentResultDTO.getData().getStatus()));
    }
}
