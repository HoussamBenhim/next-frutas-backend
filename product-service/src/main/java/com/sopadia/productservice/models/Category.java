package com.sopadia.productservice.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
public class Category {
    @Field(value = "category_name")
    private String categoryName;
    @Field(value = "sub_category_name")
    private String subCategoryName;
    @Field(value = "family_name")
    private String familyName;
}
