package theater.domain.http;


import theater.domain.entities.Order;

import java.util.List;

public class OrderResponse {
    public List<Order> orders;

    public OrderResponse() {
    }

    public OrderResponse(List<Order> orders) {
        this.orders = orders;
    }
}
