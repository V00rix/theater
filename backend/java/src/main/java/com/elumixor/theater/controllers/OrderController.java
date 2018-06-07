package com.elumixor.theater.controllers;

import com.elumixor.theater.domain.entities.Order;
import com.elumixor.theater.domain.enumeration.Checkout;
import com.elumixor.theater.domain.http.OrderResponse;
import com.elumixor.theater.repositories.OrderRepository;
import com.elumixor.theater.repositories.PerformanceRepository;
import com.elumixor.theater.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController extends ControllerBase {

    private final
    OrderRepository orderRepository;

    @Autowired
    public OrderController(PerformanceRepository performanceRepository, OrderRepository orderRepository, SeatRepository seatRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/saveRequests")
    public void saveRequests(@RequestBody ArrayList<Order> orders) {

        var grouped = orderRepository.findAll().stream()
                .collect(Collectors.groupingBy(Order::getSession,
                        Collectors.groupingBy(o -> o.client)));

        orders.forEach(o -> {
            if (o.confirmed != null) {
                grouped.forEach(((session, clientListMap) -> clientListMap.forEach((client, orders1) -> {
                    if (orders1.stream().anyMatch(ord -> ord.id.equals(o.id))) {
                        orders1.forEach(order -> {
                            order.confirmed = o.confirmed;
                            if (o.confirmed == Boolean.FALSE) {
                                order.seats = null;
                            }
                            orderRepository.save(order);
                        });
                    }
                })));
            }
        });
    }

    @GetMapping("/orders")
    public OrderResponse getAllOrders() {
        var ordersByClients = new ArrayList<Order>();
        var orders = new ArrayList<Order>(this.orderRepository.findAll());

        var allOrdersWithSameClientPerSession = new ArrayList<Order>();

        orders.stream().collect(Collectors.groupingBy(Order::getSession))
                .forEach(((session, orders1) -> {
                    orders1.stream().collect(Collectors.groupingBy(ord -> ord.client))
                            .forEach((client, orders2) -> {
                                client.print();
                                var ord = orders2.stream().reduce(
                                        orders2.stream().findFirst().orElse(new Order(client, Checkout.SELF_CHECKOUT)),
                                        (o1, o2) -> {
                                            o1.seats.addAll(o2.seats);
                                            o1.checkout = o2.checkout;
                                            if (o1.confirmed == null || o2.confirmed == null) {
                                                o1.confirmed = null;
                                            }
                                            return o1;
                                        });
                                if (ord.confirmed == null) {
                                    allOrdersWithSameClientPerSession.add(ord);
                                }
                            });
                }));
        return new OrderResponse(allOrdersWithSameClientPerSession);
    }
}
