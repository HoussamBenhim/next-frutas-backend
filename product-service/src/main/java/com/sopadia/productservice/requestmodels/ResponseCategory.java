package com.sopadia.productservice.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseCategory {
    private String categoryName;
    private String subCategoryName;
    private String familyName;
}
