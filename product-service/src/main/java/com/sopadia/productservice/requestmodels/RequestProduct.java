package com.sopadia.productservice.requestmodels;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
public class RequestProduct {
    private String name;
    private BigInteger eanCode;
    private RequestCategory category;

}
