package com.sopadia.inventoryservice;

import com.sopadia.inventoryservice.models.Inventory;
import com.sopadia.inventoryservice.repositories.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory1= Inventory.builder().quantity(2).skuCode("iphone_13").build();
			Inventory inventory2= Inventory.builder().quantity(10).skuCode("iphone_13_red").build();
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);

		};
	}
}
