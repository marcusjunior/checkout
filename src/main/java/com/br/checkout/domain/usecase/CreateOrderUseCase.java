package com.br.checkout.domain.usecase;

import com.br.checkout.domain.model.Order;
import com.br.checkout.domain.port.CheckoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderUseCase {

    private final CheckoutRepository checkoutRepository;

    public CreateOrderUseCase(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public Order createOrder(Order order) {
        return checkoutRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return checkoutRepository.findAll();
    }

}
