package com.sopadia.productservice.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestCategory {
        private String categoryName;
        private String subCategoryName;
        private String familyName;
}
