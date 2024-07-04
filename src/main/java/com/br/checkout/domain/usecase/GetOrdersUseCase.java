package com.br.checkout.domain.usecase;

import com.br.checkout.domain.model.Order;
import com.br.checkout.domain.port.CheckoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrdersUseCase {

    private final CheckoutRepository checkoutRepository;

    public GetOrdersUseCase(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public List<Order> getAllOrders() {
        return checkoutRepository.findAll();
    }
}
