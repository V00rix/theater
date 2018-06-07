package com.elumixor.theater.controllers;


import com.elumixor.theater.domain.entities.Session;
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

@RestController
@RequestMapping("/api")
public class PerformanceController extends ControllerBase {

    private final
    PerformanceRepository performanceRepository;

    @Autowired
    public PerformanceController(PerformanceRepository performanceRepository, OrderRepository orderRepository) {
        this.performanceRepository = performanceRepository;
    }

    @GetMapping("/performances")
    public PerformanceResponse getAllPerformances() {
        return new PerformanceResponse(this.performanceRepository.findAll());
    }

    @GetMapping("/sessions")
    public SessionResponse getAllSessions() {
        return new SessionResponse(new ArrayList<Session>(this.performanceRepository.findAll().stream().map(x -> x.sessions).reduce((x, y) -> {
            x.addAll(y);
            return x;
        }).orElse(new HashSet<>())));
    }


}
