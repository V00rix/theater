package theater.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theater.domain.entities.Order;
import theater.repositories.OrderRepository;
import theater.rest.ControllerBase;

@RestController
@RequestMapping("/api/order")
public class OrderController extends ControllerBase<Order, OrderRepository> {

    public OrderController(OrderRepository orderRepository) {
        super(orderRepository);
    }
}
