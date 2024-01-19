package com.sopadia.orderservice.dto;

import lombok.*;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {
    private String skuCode;
    private boolean inStock;
}
