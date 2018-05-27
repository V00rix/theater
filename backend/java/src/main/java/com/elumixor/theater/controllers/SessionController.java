package com.elumixor.theater.controllers;


import com.elumixor.theater.domain.Session;
import com.elumixor.theater.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    SessionRepository sessionRepository;


    // Get All Notes
    @GetMapping("")
    public List<Session> getAllPerformances() {
        return this.sessionRepository.findAll();
    }
}
