package com.sopadia.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data@NoArgsConstructor@AllArgsConstructor
@Builder
public class ReqOrderLineItems {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
