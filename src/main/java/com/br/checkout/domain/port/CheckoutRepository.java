package com.br.checkout.domain.port;

import com.br.checkout.domain.model.Order;

import java.util.List;

public interface CheckoutRepository {

    Order save(Order order);
    List<Order> findAll();
    void updateOrderStatus(Long id, String status);
}
