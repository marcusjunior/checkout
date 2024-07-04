package com.br.checkout.infrastructure.messaging;

import com.br.checkout.domain.port.CheckoutRepository;
import com.br.checkout.infrastructure.messaging.dto.OrderUpdatedDTO;
import com.br.checkout.infrastructure.messaging.dto.PaymentResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

public class ResultQueueConsumerTest {

    @Mock
    private CheckoutRepository checkoutRepository;

    @InjectMocks
    private ResultQueueConsumer resultQueueConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleOrderStatusUpdated() {
        PaymentResultDTO paymentResultDTO = new PaymentResultDTO();
        OrderUpdatedDTO orderUpdatedDTO = new OrderUpdatedDTO();
        orderUpdatedDTO.setId(1L);
        orderUpdatedDTO.setAmount(100.0);
        orderUpdatedDTO.setStatus("CONFIRMED");

        paymentResultDTO.setPattern("order-status-updated");
        paymentResultDTO.setData(orderUpdatedDTO);

        resultQueueConsumer.handleOrderStatusUpdated(paymentResultDTO);

        verify(checkoutRepository).updateOrderStatus(anyLong(), anyString());
    }
}
