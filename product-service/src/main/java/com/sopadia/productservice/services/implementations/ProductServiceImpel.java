package com.sopadia.productservice.services.implementations;

import com.sopadia.productservice.models.Product;
import com.sopadia.productservice.repositories.ProductRepository;
import com.sopadia.productservice.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpel implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public void addNewProduct(Product product) {
        product = productRepository.save(product);
        log.info(String.format("product with name : %s and id : %s is saved to database.", product.getProductName(), product.getId() ));
    }
    @Override
    public List<Product> getAllProducts(Pageable pageable) {
        Page<Product> productList = productRepository.findAll(pageable);
        return productList.stream().toList();
    }

    @Override
    public void deleteProduct(String id) {
            productRepository.deleteById(id);
    }


}
