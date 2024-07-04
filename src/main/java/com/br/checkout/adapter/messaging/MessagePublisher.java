package com.br.checkout.adapter.messaging;

import com.br.checkout.domain.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.br.checkout.infrastructure.config.MessagingConfig.QUEUE;

@Component
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrder(Order order) {
        rabbitTemplate.convertAndSend(QUEUE, order);
    }

}
