package com.br.checkout.application.service;

import com.br.checkout.adapter.messaging.MessagePublisher;
import com.br.checkout.domain.model.Order;
import com.br.checkout.domain.usecase.CancelOrderUseCase;
import com.br.checkout.domain.usecase.CreateOrderUseCase;
import com.br.checkout.domain.usecase.GetOrdersUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrdersUseCase getOrdersUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final MessagePublisher messagePublisher;

    public CheckoutService(CreateOrderUseCase createOrderUseCase, GetOrdersUseCase getOrdersUseCase, CancelOrderUseCase cancelOrderUseCase, MessagePublisher messagePublisher) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrdersUseCase = getOrdersUseCase;
        this.messagePublisher = messagePublisher;
        this.cancelOrderUseCase = cancelOrderUseCase;
    }

    public Order createOrder(Order order) {
        Order createdOrder = createOrderUseCase.createOrder(order);
        messagePublisher.publishOrder(createdOrder);
        return createdOrder;
    }

    public List<Order> getAllOrders() {
        return getOrdersUseCase.getAllOrders();
    }

    public void cancelOrder(Long id) {
        cancelOrderUseCase.cancelOrder(id);
    }
}
