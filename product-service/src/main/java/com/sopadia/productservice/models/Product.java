package com.sopadia.productservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;

@Document(value = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class Product {
    @Id
    private String id;
    @Field(value = "product_name")
    private String productName;
    @Field(value = "category")
    private Category category;
    @Field(value = "price")
    private Integer price;
    @Field(value = "unity")
    private PriceUnit  unite;

    @Data
    @AllArgsConstructor
    public static class Category {
        @Field(value = "category_name")
        private String categoryName;
        @Field(value = "family")
        private String family;
    }

    public static enum PriceUnit{
        KG,
        Gramme,
        SAC,
        Barquette,
        Botte,
        Piece,
        Carton,
        Cagette,
        Caisse,
        Plateau

    }
}
