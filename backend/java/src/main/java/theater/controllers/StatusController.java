package theater.controllers;

import org.springframework.web.bind.annotation.*;
import theater.domain.http.Status;


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

