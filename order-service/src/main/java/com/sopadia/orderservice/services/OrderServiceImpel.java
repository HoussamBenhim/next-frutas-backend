package com.sopadia.orderservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sopadia.orderservice.dto.InventoryDto;
import com.sopadia.orderservice.models.Order;
import com.sopadia.orderservice.models.OrderLineItems;
import com.sopadia.orderservice.repositories.OrderRepository;
import com.sopadia.orderservice.dto.ReqOrderLineItems;
import com.sopadia.orderservice.dto.RequestOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpel implements OrderService{
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;
    @Override
    public void addNewOrder(RequestOrder requestOrder) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = requestOrder.getReqOrderLineItems()
                .stream()
                .map(this::mapToRequest)
                .toList();
        order.setOrderLineItemList(orderLineItemsList);

        List<String> skuCodeList = requestOrder.getReqOrderLineItems().stream().map(ReqOrderLineItems::getSkuCode).toList();
        Mono<Object[]> objectsResponse = webClientBuilder.build().get().uri("/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Object[].class);
        Object[] objects = objectsResponse.block();
        List<InventoryDto> inventoryDtoList = Arrays.stream(objects).map(object -> objectMapper.convertValue(object, InventoryDto.class)).toList();
        boolean allProductMatch =inventoryDtoList.stream().allMatch(inventoryDto -> inventoryDto.isInStock());
        if(!inventoryDtoList.isEmpty() && allProductMatch==true){
            orderRepository.save(order);
        }else {
            throw new RuntimeException("Product is out of stock!");
        }
    }
    private OrderLineItems mapToRequest(ReqOrderLineItems reqOrderLineItems) {
        return OrderLineItems.builder()
                .price(reqOrderLineItems.getPrice())
                .skuCode(reqOrderLineItems.getSkuCode())
                .quantity(reqOrderLineItems.getQuantity())
                .build();
    }
}
