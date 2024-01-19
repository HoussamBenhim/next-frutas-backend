package com.sopadia.orderservice.services;

import com.sopadia.orderservice.dto.RequestOrder;

public interface OrderService {
    void addNewOrder(RequestOrder requestOrder);
}
