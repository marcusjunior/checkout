package com.br.checkout.api.controller;

import com.br.checkout.api.controller.converter.OrderConverter;
import com.br.checkout.api.controller.dto.OrderDTO;
import com.br.checkout.api.controller.dto.OrderCreateRequestDTO;
import com.br.checkout.application.service.CheckoutService;
import com.br.checkout.domain.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/checkout")
@Tag(name = "Checkout", description = "Checkout API")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/order")
    @Operation(summary = "Create a new order", description = "Creates a new order and sends it for payment validation")
    public OrderDTO createOrder(@RequestBody OrderCreateRequestDTO OrderCreateRequestDTO) {
        Order order = OrderConverter.toEntity(OrderCreateRequestDTO);
        Order createdOrder = checkoutService.createOrder(order);
        return OrderConverter.toDTO(createdOrder);
    }

    @GetMapping("/orders")
    @Operation(summary = "Get all orders", description = "Retrieves all orders")
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = checkoutService.getAllOrders();
        return orders.stream()
                .map(OrderConverter::toDTO)
                .collect(Collectors.toList());
    }

    @PatchMapping("/order/{id}")
    @Operation(summary = "Cancel a order", description = "Cancel a order without validation")
    public ResponseEntity cancelOrder(@PathVariable Long id) {
        checkoutService.cancelOrder(id);
        return ResponseEntity.ok().build();
    }
}