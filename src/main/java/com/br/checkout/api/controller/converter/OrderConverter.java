package com.br.checkout.api.controller.converter;

import com.br.checkout.api.controller.dto.OrderDTO;
import com.br.checkout.api.controller.dto.OrderCreateRequestDTO;
import com.br.checkout.domain.model.Order;
import com.br.checkout.domain.model.OrderStatus;

public class OrderConverter {

    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setAmount(order.getAmount());
        dto.setStatus(order.getStatus().name());
        return dto;
    }

    public static Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setAmount(dto.getAmount());
        order.setStatus(OrderStatus.valueOf(dto.getStatus()));
        return order;
    }

    public static Order toEntity(OrderCreateRequestDTO dto) {
        Order order = new Order();
        order.setAmount(dto.getAmount());
        order.setStatus(OrderStatus.PENDENTE);
        return order;
    }

}
