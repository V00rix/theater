package com.elumixor.theater.controllers;


import com.elumixor.theater.domain.entities.*;
import com.elumixor.theater.domain.enumeration.Checkout;
import com.elumixor.theater.domain.http.Status;
import com.elumixor.theater.repositories.*;
import com.elumixor.theater.repositories.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController extends ControllerBase {

    private final
    PerformanceRepository performanceRepository;

    private final
    ClientsRepository clientsRepository;

    private final
    HallRepository hallRepository;

    private final
    RowRepository rowRepository;

    private final
    SeatRepository seatRepository;

    @Autowired
    public ReservationController(PerformanceRepository performanceRepository, OrderRepository orderRepository, ClientsRepository clientsRepository, HallRepository hallRepository, RowRepository rowRepository, SeatRepository seatRepository) {
        this.performanceRepository = performanceRepository;
        this.clientsRepository = clientsRepository;
        this.hallRepository = hallRepository;
        this.rowRepository = rowRepository;
        this.seatRepository = seatRepository;
    }

    @PostMapping("")
    public String createOrder() {
        var status = Status.status;
        var user = status.user;
        Client client;

        var searched = this.clientsRepository.findByEmail(user.contact);
        client = searched.orElseGet(() -> new Client(user));

        var checkout = Checkout.valueOf(status.selected_checkout);
        var order = new Order(client, checkout);

        var seats = new ArrayList<Seat>();

        var performance = this.performanceRepository.findAll().get(status.selected_performance);

        Session session = new Session();
        var sessionIterator = performance.sessions.iterator();

        for (int i = 0; i <= status.selected_session; i++) {
            session = sessionIterator.next();
        }

        var hall = hallRepository.findById((long) 1).orElse(new Hall());

        Session finalSession = session;

        status.selected_seats.forEach(s -> {
            System.out.println(s.row + ", " + s.seat);
        });

        status.selected_seats.forEach(s -> {
            var row = rowRepository.findByNumberAndHall(s.row, hall).orElse(new Row());

            seats.add(new Seat(order, row, finalSession, s.seat));
        });

        seatRepository.saveAll(seats);

        status.selected_seats = new ArrayList<>();

        return Long.toHexString(order.id);
    }

}
