package com.br.checkout.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements Serializable {

    private Long id;
    private BigDecimal amount;
    private OrderStatus status;

}
