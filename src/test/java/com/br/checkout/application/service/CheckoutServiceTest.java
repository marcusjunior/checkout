package com.br.checkout.application.service;

import com.br.checkout.adapter.messaging.MessagePublisher;
import com.br.checkout.domain.model.Order;
import com.br.checkout.domain.model.OrderStatus;
import com.br.checkout.domain.usecase.CancelOrderUseCase;
import com.br.checkout.domain.usecase.CreateOrderUseCase;
import com.br.checkout.domain.usecase.GetOrdersUseCase;
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
import static org.mockito.Mockito.*;

public class CheckoutServiceTest {

    @Mock
    private CreateOrderUseCase createOrderUseCase;

    @Mock
    private GetOrdersUseCase getOrdersUseCase;

    @Mock
    private CancelOrderUseCase cancelOrderUseCase;

    @Mock
    private MessagePublisher messagePublisher;

    @InjectMocks
    private CheckoutService checkoutService;

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

        when(createOrderUseCase.createOrder(any(Order.class))).thenReturn(order);

        Order createdOrder = checkoutService.createOrder(order);

        assertEquals(order.getId(), createdOrder.getId());
        assertEquals(order.getAmount(), createdOrder.getAmount());
        assertEquals(order.getStatus(), createdOrder.getStatus());
        verify(messagePublisher).publishOrder(createdOrder);
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

        when(getOrdersUseCase.getAllOrders()).thenReturn(orders);

        List<Order> allOrders = checkoutService.getAllOrders();

        assertEquals(2, allOrders.size());
        assertEquals(order1.getId(), allOrders.get(0).getId());
        assertEquals(order2.getId(), allOrders.get(1).getId());
    }

    @Test
    void testCancelOrder() {
        Long orderId = 1L;

        doNothing().when(cancelOrderUseCase).cancelOrder(orderId);

        checkoutService.cancelOrder(orderId);

        verify(cancelOrderUseCase).cancelOrder(orderId);
    }
}
