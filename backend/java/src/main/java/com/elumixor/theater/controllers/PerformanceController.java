package com.elumixor.theater.controllers;


import com.elumixor.theater.domain.entities.Order;
import com.elumixor.theater.domain.entities.Session;
import com.elumixor.theater.domain.enumeration.Checkout;
import com.elumixor.theater.domain.http.OrderResponse;
import com.elumixor.theater.domain.http.PerformanceResponse;
import com.elumixor.theater.domain.http.SessionResponse;
import com.elumixor.theater.repositories.OrderRepository;
import com.elumixor.theater.repositories.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PerformanceController extends ControllerBase {

    private final
    PerformanceRepository performanceRepository;

    private final
    OrderRepository orderRepository;

    @Autowired
    public PerformanceController(PerformanceRepository performanceRepository, OrderRepository orderRepository) {
        this.performanceRepository = performanceRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/performances")
    public PerformanceResponse getAllPerformances() {
        return new PerformanceResponse(this.performanceRepository.findAll());
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

    @GetMapping("/sessions")
    public SessionResponse getAllSessions() {
        return new SessionResponse(new ArrayList<Session>(this.performanceRepository.findAll().stream().map(x -> x.sessions).reduce((x, y) -> {
            x.addAll(y);
            return x;
        }).orElse(new HashSet<>())));
    }


}
