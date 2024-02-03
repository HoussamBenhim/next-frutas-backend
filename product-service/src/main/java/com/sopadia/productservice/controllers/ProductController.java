package com.sopadia.productservice.controllers;

import com.sopadia.productservice.mappers.ProductMapper;
import com.sopadia.productservice.models.Product;
import com.sopadia.productservice.requestmodels.ProductDto;
import com.sopadia.productservice.requestmodels.ResponseProduct;
import com.sopadia.productservice.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
public class ProductController {

    public static final String FILTER_NAME = "productName";
    private final ProductService productService;

    @Autowired
    private final ProductMapper productMapper;
    @PostMapping( consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody ProductDto productDto){
        Product product = productMapper.toProduct(productDto);
        productService.addNewProduct(product);
        return "Created Product";
    }
    @GetMapping("/all")
    public List<ProductDto> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize){
        PageRequest of = PageRequest.of(page, pageSize,Sort.by(FILTER_NAME).descending());
        List<Product> productList=  productService.getAllProducts(of);
        List<ProductDto> productDtoList = productList.stream().map(product -> productMapper.toProductDto(product)).toList();
        return productDtoList;
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
    }

}
