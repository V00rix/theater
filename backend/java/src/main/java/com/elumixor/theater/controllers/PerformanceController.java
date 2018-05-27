package com.elumixor.theater.controllers;


import com.elumixor.theater.domain.Performance;
import com.elumixor.theater.repositories.PerformanceRepository;
import com.elumixor.theater.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController {

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    SessionRepository sessionRepository;


    // Get All Notes
    @GetMapping("")
    public List<Performance> getAllPerformances() {
        return this.performanceRepository.findAll();
    }
}
