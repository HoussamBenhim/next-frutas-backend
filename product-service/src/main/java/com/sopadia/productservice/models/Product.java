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
    @Field(value = "name")
    private String name;
    @Field(value = "code_ean")
    private BigInteger eanCode;
    @Field(value = "category")
    private Category category;

}
