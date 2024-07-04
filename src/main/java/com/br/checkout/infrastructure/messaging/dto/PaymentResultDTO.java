package com.br.checkout.infrastructure.messaging.dto;

import lombok.Data;

@Data
public class PaymentResultDTO {

    private String pattern;
    private OrderUpdatedDTO data;

}
