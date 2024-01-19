package com.sopadia.inventoryservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
