package com.sopadia.productservice.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseProduct {
    private String id;
    private String name;
    private BigInteger eanCode;
    private ResponseCategory categoryName;
}
