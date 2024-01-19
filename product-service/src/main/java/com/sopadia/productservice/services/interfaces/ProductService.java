package com.sopadia.productservice.services.interfaces;

import com.sopadia.productservice.requestmodels.RequestProduct;
import com.sopadia.productservice.requestmodels.ResponseProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    void addNewProduct(RequestProduct product);

    List<ResponseProduct> getAllProducts(Pageable pageable);

    void deleteProduct(String id);
}
