package com.elumixor.theater.controllers;


import com.elumixor.theater.domain.Performance;
import com.elumixor.theater.repositories.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PerformancesController {

    @Autowired
    PerformanceRepository performanceRepository;


    // Get All Notes
    @GetMapping("/performances")
    public List<Performance> getAllNotes() {
        return this.performanceRepository.findAll();
    }
}
