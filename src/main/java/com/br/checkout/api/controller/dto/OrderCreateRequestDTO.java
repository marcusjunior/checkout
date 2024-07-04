package com.br.checkout.api.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderCreateRequestDTO {

    private BigDecimal amount;

}
