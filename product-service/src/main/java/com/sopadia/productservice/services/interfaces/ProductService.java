package com.sopadia.productservice.services.interfaces;

import com.sopadia.productservice.models.Product;
import com.sopadia.productservice.requestmodels.ResponseProduct;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {
    void addNewProduct(Product product);

    List<Product> getAllProducts(Pageable pageable);

    void deleteProduct(String id);
}
