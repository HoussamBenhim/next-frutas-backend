package com.sopadia.inventoryservice.services;

import com.sopadia.inventoryservice.dto.InventoryResponse;
import com.sopadia.inventoryservice.models.Inventory;
import com.sopadia.inventoryservice.repositories.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpel implements InventoryService{
    private final InventoryRepository inventoryRepository;
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        List<Inventory> bySkuCodeIn = inventoryRepository.findBySkuCodeIn(skuCode);
        var inventoryResponseList = bySkuCodeIn.stream().map(inventory ->
                InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0).build()
        ).toList();
        log.info(inventoryResponseList.toString());
        return inventoryResponseList;
    }
}
