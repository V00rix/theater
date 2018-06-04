package com.elumixor.theater.controllers;

import com.elumixor.theater.domain.entities.Order;
import com.elumixor.theater.repositories.OrderRepository;
import com.elumixor.theater.repositories.PerformanceRepository;
import com.elumixor.theater.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController extends ControllerBase {


    private final
    PerformanceRepository performanceRepository;

    private final
    OrderRepository orderRepository;

    private final
    SeatRepository seatRepository;

    @Autowired
    public OrderController(PerformanceRepository performanceRepository, OrderRepository orderRepository, SeatRepository seatRepository) {
        this.performanceRepository = performanceRepository;
        this.orderRepository = orderRepository;
        this.seatRepository = seatRepository;
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

        //        grouped.get(o.getSession()).get(o.client).forEach(ord -> {
        //            ord.print();
        //        });
        //    });
    }
}
