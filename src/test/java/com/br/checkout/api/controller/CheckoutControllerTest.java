package com.br.checkout.api.controller;

import com.br.checkout.api.controller.dto.OrderCreateRequestDTO;
import com.br.checkout.api.controller.dto.OrderDTO;
import com.br.checkout.application.service.CheckoutService;
import com.br.checkout.domain.model.Order;
import com.br.checkout.domain.model.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.*;

public class CheckoutControllerTest {

    @Mock
    private CheckoutService checkoutService;

    @InjectMocks
    private CheckoutController checkoutController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(checkoutController).build();
    }

    @Test
    void testCreateOrder() throws Exception {
        OrderCreateRequestDTO requestDTO = new OrderCreateRequestDTO();
        requestDTO.setAmount(BigDecimal.valueOf(100.0));

        Order order = new Order();
        order.setId(1L);
        order.setAmount(BigDecimal.valueOf(100.0));
        order.setStatus(OrderStatus.PENDENTE);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setAmount(BigDecimal.valueOf(100.0));
        orderDTO.setStatus("PENDING");

        when(checkoutService.createOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/checkout/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.status").value("PENDENTE"));
    }

    @Test
    void testGetAllOrders() throws Exception {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setAmount(BigDecimal.valueOf(100.0));
        order1.setStatus(OrderStatus.PENDENTE);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setAmount(BigDecimal.valueOf(200.0));
        order2.setStatus(OrderStatus.CONFIRMADO);

        List<Order> orders = Arrays.asList(order1, order2);

        when(checkoutService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/checkout/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].amount").value(100.0))
                .andExpect(jsonPath("$[0].status").value("PENDENTE"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].amount").value(200.0))
                .andExpect(jsonPath("$[1].status").value("CONFIRMADO"));
    }

    @Test
    void testCancelOrder() throws Exception {
        Long orderId = 1L;

        doNothing().when(checkoutService).cancelOrder(orderId);

        mockMvc.perform(patch("/checkout/order/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(checkoutService).cancelOrder(orderId);
    }
}
