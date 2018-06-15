package theater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Theater;
import theater.repositories.TheaterRepository;

@RestController
@RequestMapping("/api/theater")
public class TheaterController extends ControllerBase<Theater, TheaterRepository> {

    private final
    TheaterRepository theaterRepository;

    @Autowired
    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @Override
    public TheaterRepository repository() {
        return theaterRepository;
    }
}
