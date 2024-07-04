package com.br.checkout.infrastructure.messaging.dto;

import lombok.Data;

@Data
public class OrderUpdatedDTO {

    private Long id;
    private Double amount;
    private String status;

}
