package com.elumixor.theater.controllers;


import com.elumixor.theater.domain.http.PerformanceResponse;
import com.elumixor.theater.repositories.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController extends ControllerBase {

    private final
    PerformanceRepository performanceRepository;

    @Autowired
    public PerformanceController(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    @GetMapping("")
    public PerformanceResponse getAllPerformances() {
        return new PerformanceResponse(this.performanceRepository.findAll());
    }

}
