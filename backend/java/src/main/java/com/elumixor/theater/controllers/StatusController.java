package com.elumixor.theater.controllers;

import com.elumixor.theater.domain.http.Status;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/status")
public class StatusController {


    @GetMapping("")
    public Status getStatus() {
        return Status.status;
    }

    @PostMapping("")
    public void setStatus(@RequestBody Status status) {
        Status.status = status;
    }
}

