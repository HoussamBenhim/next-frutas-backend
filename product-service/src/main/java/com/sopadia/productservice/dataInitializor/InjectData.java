package com.sopadia.productservice.dataInitializor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sopadia.productservice.models.Product;
import com.sopadia.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class InjectData implements CommandLineRunner {

    private final ProductRepository productRepository;


    public InjectData(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = readDataFromFile("data.json");
         productRepository.saveAll(products);
    }

    private static List<Product> readDataFromFile(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource(fileName);
        Product[] listOfProduct = mapper.readValue(resource.getInputStream(), Product[].class);
        return Arrays.asList(listOfProduct);
    }

}
