package com.sopadia.productservice.controllers;

import com.sopadia.productservice.requestmodels.RequestProduct;
import com.sopadia.productservice.requestmodels.ResponseProduct;
import com.sopadia.productservice.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping( consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody RequestProduct requestProduct){
        productService.addNewProduct(requestProduct);
        return "Created Product";
    }
    @GetMapping("/all")
    public List<ResponseProduct> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize){
        PageRequest of = PageRequest.of(page, pageSize,Sort.by("name").descending());
        return  productService.getAllProducts(of);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
    }

}
