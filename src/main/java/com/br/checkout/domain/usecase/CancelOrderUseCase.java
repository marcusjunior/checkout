package com.br.checkout.domain.usecase;

import com.br.checkout.domain.model.OrderStatus;
import com.br.checkout.domain.port.CheckoutRepository;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderUseCase {

    private final CheckoutRepository checkoutRepository;

    public CancelOrderUseCase(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public void cancelOrder(Long id) {
        checkoutRepository.updateOrderStatus(id, OrderStatus.CANCELADO.toString());
    }

}
