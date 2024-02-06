package com.sopadia.productservice.requestmodels;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String productName;
    private CategoryDto category;
    private double price;
    private String priceUnit;
    private String origin;
    private String description;
    @Data
    @AllArgsConstructor
    public static class CategoryDto {
        private String categoryName;
        private String familyName;
    }
}
