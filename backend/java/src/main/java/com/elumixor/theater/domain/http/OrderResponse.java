package com.elumixor.theater.domain.http;

import com.elumixor.theater.domain.entities.Order;
import com.elumixor.theater.domain.entities.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    public List<Order> orders;

    public OrderResponse() {
    }

    public OrderResponse(List<Order> orders) {
        this.orders = orders;
    }
}
