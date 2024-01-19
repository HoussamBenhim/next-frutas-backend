package com.sopadia.productservice.services.implementations;

import com.sopadia.productservice.models.Category;
import com.sopadia.productservice.models.Product;
import com.sopadia.productservice.repositories.ProductRepository;
import com.sopadia.productservice.requestmodels.RequestCategory;
import com.sopadia.productservice.requestmodels.RequestProduct;
import com.sopadia.productservice.requestmodels.ResponseCategory;
import com.sopadia.productservice.requestmodels.ResponseProduct;
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
    public void addNewProduct(RequestProduct reqProduct) {
        Product product = productRepository.save(convertRequestProductToProductObject(reqProduct));
        log.info(String.format("product with name : %s and id : %s is saved to database.", product.getName(), product.getId() ));
    }
    @Override
    public List<ResponseProduct> getAllProducts(Pageable pageable) {
        Page<Product> productList = productRepository.findAll(pageable);
        List<ResponseProduct> responseProductList = productList.stream().map(product -> convertProductToResponseProductObj(product))
                .toList();
        return responseProductList;
    }

    @Override
    public void deleteProduct(String id) {
            productRepository.deleteById(id);
    }


    private static Product convertRequestProductToProductObject(RequestProduct reqProduct){
        return Product.builder().name(reqProduct.getName())
                .eanCode(reqProduct.getEanCode())
                .category(convertRequestCategoryToCategory(reqProduct.getCategory())).build();
    }
    private static Category convertRequestCategoryToCategory(RequestCategory requestCategory){
        return Category.builder()
                        .categoryName(requestCategory.getCategoryName())
                                .subCategoryName(requestCategory.getSubCategoryName())
                                        .familyName(requestCategory.getFamilyName()).build();
    };
    private static ResponseProduct convertProductToResponseProductObj(Product product){
        return ResponseProduct.builder().id(product.getId())
                .categoryName(convertCategoryToResponseCategory(product.getCategory()))
                .name(product.getName())
                .eanCode(product.getEanCode()).build();
    }
    private static ResponseCategory convertCategoryToResponseCategory(Category category){
        return ResponseCategory.builder()
                        .categoryName(category.getCategoryName())
                                .subCategoryName(category.getSubCategoryName())
                                        .familyName(category.getFamilyName()).build();
    }
}
