package com.br.checkout.domain.usecase;

import com.br.checkout.domain.port.CheckoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class CancelOrderUseCaseTest {

    @Mock
    private CheckoutRepository checkoutRepository;

    @InjectMocks
    private CancelOrderUseCase cancelOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCancelOrder() {
        Long orderId = 1L;

        cancelOrderUseCase.cancelOrder(orderId);

        verify(checkoutRepository).updateOrderStatus(orderId, "CANCELADO");
    }
}
