package theater.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theater.domain.entities.Order;
import theater.repositories.OrderRepository;

@RestController
@RequestMapping("/api/order")
public class OrderController extends ControllerBase<Order, OrderRepository> {
    private final
    OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderRepository repository() {
        return orderRepository;
    }
}
