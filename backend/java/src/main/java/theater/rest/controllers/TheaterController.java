package theater.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Theater;
import theater.repositories.TheaterRepository;
import theater.rest.ControllerBase;

@RestController
@RequestMapping("/api/theater")
public class TheaterController extends ControllerBase<Theater, TheaterRepository> {
    @Autowired
    public TheaterController(TheaterRepository theaterRepository) {
        super(theaterRepository);
    }
}
