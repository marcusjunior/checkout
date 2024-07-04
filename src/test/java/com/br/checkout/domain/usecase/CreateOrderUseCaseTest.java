package com.br.checkout.domain.usecase;

import com.br.checkout.domain.model.Order;
import com.br.checkout.domain.model.OrderStatus;
import com.br.checkout.domain.port.CheckoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateOrderUseCaseTest {

    @Mock
    private CheckoutRepository checkoutRepository;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setAmount(BigDecimal.valueOf(100.0));
        order.setStatus(OrderStatus.PENDENTE);

        when(checkoutRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = createOrderUseCase.createOrder(order);

        assertEquals(order.getId(), createdOrder.getId());
        assertEquals(order.getAmount(), createdOrder.getAmount());
        assertEquals(order.getStatus(), createdOrder.getStatus());
    }

    @Test
    void testGetAllOrders() {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setAmount(BigDecimal.valueOf(100.0));
        order1.setStatus(OrderStatus.PENDENTE);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setAmount(BigDecimal.valueOf(200.0));
        order2.setStatus(OrderStatus.CONFIRMADO);

        List<Order> orders = Arrays.asList(order1, order2);

        when(checkoutRepository.findAll()).thenReturn(orders);

        List<Order> allOrders = createOrderUseCase.getAllOrders();

        assertEquals(2, allOrders.size());
        assertEquals(order1.getId(), allOrders.get(0).getId());
        assertEquals(order2.getId(), allOrders.get(1).getId());
    }
}
