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
    private Integer price;
    private String priceUnit;

    @Data
    @AllArgsConstructor
    public static class CategoryDto {
        private String categoryName;
        private String familyName;
    }
}
