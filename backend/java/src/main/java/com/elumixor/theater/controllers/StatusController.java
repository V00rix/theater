package com.elumixor.theater.controllers;

import com.elumixor.theater.domain.http.StatusResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/status")
public class StatusController {

    // Get All Notes
    @GetMapping("")
    public StatusResponse getStatus() {
        return new StatusResponse();
    }
}

